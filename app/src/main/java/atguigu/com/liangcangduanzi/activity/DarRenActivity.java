package atguigu.com.liangcangduanzi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.DaRen_FragmentAdapter;
import atguigu.com.liangcangduanzi.bean.Daren_ItmsBean;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.CommondFragment;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.FenSiFragment;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.GuanZhuFragment;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.LikeFragment;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class DarRenActivity extends AppCompatActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.imageview)
    ImageView imageview;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.bt_guanzhu)
    Button btGuanzhu;
    @InjectView(R.id.bt_sixin)
    Button btSixin;
    @InjectView(R.id.like_acount)
    TextView likeAcount;
    @InjectView(R.id.ll_xihuan)
    LinearLayout llXihuan;
    @InjectView(R.id.tuijian_acount)
    TextView tuijianAcount;
    @InjectView(R.id.ll_tuijian)
    LinearLayout llTuijian;
    @InjectView(R.id.guanzhu_acount)
    TextView guanzhuAcount;
    @InjectView(R.id.llguanzhu)
    LinearLayout llguanzhu;
    @InjectView(R.id.fensi_acount)
    TextView fensiAcount;
    @InjectView(R.id.ll_fensi)
    LinearLayout llFensi;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.activity_dar_ren)
    LinearLayout activityDarRen;
    @InjectView(R.id.tv_xihuan)
    TextView tvXihuan;
    @InjectView(R.id.tv_tuijian)
    TextView tvTuijian;
    @InjectView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @InjectView(R.id.tv_fensi)
    TextView tvFensi;

    private TextView tv_duty;

    private String name;
    private String duty;
    private String imageUrl;
    public int uid;

    private List<Fragment> fragments;
    private DaRen_FragmentAdapter adapter;
    private String url;
    private Daren_ItmsBean daren_itmsBean;
    private int like_count;
    private int recommendation_count;
    private int following_count;
    private int followed_count;


    public String getItemUrl1() {
        return JieKouUtils.XIHUANHEAN + uid + JieKouUtils.XIHUANWEI;
    }

    public String getItemUrl2() {
        return JieKouUtils.TUIJIANTOU + uid + JieKouUtils.TUIJIANWEI ;
    }

    public String getItemUrl3() {
        return JieKouUtils.GUANZHUTOU + uid +JieKouUtils.GAUNZHUWEI;
    }

    public String getItemUrl4() {
        return JieKouUtils.FENSITOU + uid + JieKouUtils.FEISIWEI;
    }

    public String itemUrl1;
    public String itemUrl2 ;
    public String itemUrl3;
    public String itemUrl4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_ren);
        ButterKnife.inject(this);

        tv_duty = (TextView) findViewById(R.id.tv_duty);

        name = getIntent().getStringExtra("username");
        duty = getIntent().getStringExtra("duty");
        imageUrl = getIntent().getStringExtra("imageUrl");
        uid = getIntent().getIntExtra("uid",-1);

        url = JieKouUtils.XIHUANHEAN + uid + JieKouUtils.XIHUANWEI;

        getItemUrl1();
        getItemUrl2();
        getItemUrl3();
        getItemUrl4();

        getItemUrl1();

        //设置默认
        setDefult();




        initData();

        initListener();
        initFragment();
        getDataFromNet();

        viewPager.setCurrentItem(1);

    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        progressData(response);

                    }
                });
    }

    private void progressData(String json) {

        daren_itmsBean = new Gson().fromJson(json, Daren_ItmsBean.class);

        like_count = Integer.parseInt(daren_itmsBean.getData().getItems().getLike_count());
        recommendation_count = Integer.parseInt(daren_itmsBean.getData().getItems().getRecommendation_count());
        following_count = Integer.parseInt(daren_itmsBean.getData().getItems().getFollowing_count());
        followed_count = Integer.parseInt(daren_itmsBean.getData().getItems().getFollowed_count());



        likeAcount.setText(like_count+"");
        tuijianAcount.setText(recommendation_count+"");
        guanzhuAcount.setText(following_count+"");
        fensiAcount.setText(followed_count+"");


    }


    private void setDefult() {

        tvXihuan.setTextColor(Color.WHITE);
        likeAcount.setTextColor(Color.WHITE);
        llXihuan.setBackgroundColor(Color.GRAY);

        tvTuijian.setTextColor(Color.WHITE);
        tuijianAcount.setTextColor(Color.WHITE);
        llTuijian.setBackgroundColor(Color.GRAY);

        tvGuanzhu.setTextColor(Color.WHITE);
        guanzhuAcount.setTextColor(Color.WHITE);
        llguanzhu.setBackgroundColor(Color.GRAY);

        tvFensi.setTextColor(Color.WHITE);
        fensiAcount.setTextColor(Color.WHITE);
        llFensi.setBackgroundColor(Color.GRAY);

    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                swicthPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void swicthPager(int position) {
        switch (position) {
            case 0:
                setDefult();
                tvXihuan.setTextColor(Color.GRAY);
                likeAcount.setTextColor(Color.GRAY);
                llXihuan.setBackgroundColor(Color.WHITE);

                if( like_count != 0) {
                    itemUrl1 = JieKouUtils.XIHUANHEAN + uid + JieKouUtils.XIHUANWEI;
                    likeFragment.getUrl(itemUrl1);
                    Log.e("TAG","itemUrl1 == " + itemUrl1 );

                }else {

                }

                break;
            case 1:

                setDefult();
                tvTuijian.setTextColor(Color.GRAY);
                tuijianAcount.setTextColor(Color.GRAY);

                llTuijian.setBackgroundColor(Color.WHITE);

                if(  recommendation_count!= 0) {
                    itemUrl2 = JieKouUtils.TUIJIANTOU + uid + JieKouUtils.TUIJIANWEI;
                }else {

                }

                break;
            case 2:

                setDefult();
                tvGuanzhu.setTextColor(Color.GRAY);
                guanzhuAcount.setTextColor(Color.GRAY);
                llguanzhu.setBackgroundColor(Color.WHITE);

                if( following_count != 0) {
                    itemUrl3 = JieKouUtils.GUANZHUTOU + uid +JieKouUtils.GAUNZHUWEI;
                    guanzhuFragment.getUrl(itemUrl3);

                }else {

                }

                break;
            case 3:

                setDefult();
                tvFensi.setTextColor(Color.GRAY);
                fensiAcount.setTextColor(Color.GRAY);
                llFensi.setBackgroundColor(Color.WHITE);

                if( followed_count != 0) {
                    itemUrl4 = JieKouUtils.FENSITOU + uid + JieKouUtils.FEISIWEI;
                    fensiFragment.getUrl(itemUrl4);

                }else {

                }

                break;
        }

    }

    LikeFragment likeFragment = new LikeFragment();
    CommondFragment commondFragment = new CommondFragment();
    GuanZhuFragment guanzhuFragment = new GuanZhuFragment();
    FenSiFragment fensiFragment = new FenSiFragment();

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(likeFragment);
        fragments.add(commondFragment);
        fragments.add(guanzhuFragment);
        fragments.add(fensiFragment);


        adapter = new DaRen_FragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        tvUsername.setText(name);
        tv_duty.setText(duty);
        tvContent.setText(name);

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(imageview);
    }


    @OnClick({R.id.iv_back, R.id.bt_guanzhu, R.id.bt_sixin, R.id.ll_xihuan, R.id.ll_tuijian, R.id.llguanzhu, R.id.ll_fensi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_guanzhu:
                Toast.makeText(DarRenActivity.this, "DarRenACtivity", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_sixin:
                break;
            case R.id.ll_xihuan:
                viewPager.setCurrentItem(0);
                swicthPager(0);

                break;
            case R.id.ll_tuijian:
                viewPager.setCurrentItem(1);
                swicthPager(1);

                break;
            case R.id.llguanzhu:
                viewPager.setCurrentItem(2);
                swicthPager(2);

                break;
            case R.id.ll_fensi:
                viewPager.setCurrentItem(3);
                swicthPager(3);

                break;
        }
    }

}
