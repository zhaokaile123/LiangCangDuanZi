package atguigu.com.liangcangduanzi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.DaRen_FragmentAdapter;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.CommondFragment;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.FenSiFragment;
import atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4.GuanZhuFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

    private List<Fragment> fragments;
    private DaRen_FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_ren);
        ButterKnife.inject(this);

        tv_duty = (TextView) findViewById(R.id.tv_duty);

        name = getIntent().getStringExtra("username");
        duty = getIntent().getStringExtra("duty");
        imageUrl = getIntent().getStringExtra("imageUrl");
        Log.e("TAG", "" + duty);

        initData();
        initFragment();
        initListener();
        setDefult();
        swicthPager(0);
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

                break;
            case 1:

                setDefult();
                tvTuijian.setTextColor(Color.GRAY);
                tuijianAcount.setTextColor(Color.GRAY);

                llTuijian.setBackgroundColor(Color.WHITE);

                break;
            case 2:

                setDefult();
                tvGuanzhu.setTextColor(Color.GRAY);
                guanzhuAcount.setTextColor(Color.GRAY);
                llguanzhu.setBackgroundColor(Color.WHITE);

                break;
            case 3:

                setDefult();
                tvFensi.setTextColor(Color.GRAY);
                fensiAcount.setTextColor(Color.GRAY);
                llFensi.setBackgroundColor(Color.WHITE);

                break;
        }

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new CommondFragment());
        fragments.add(new GuanZhuFragment());
        fragments.add(new FenSiFragment());

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

                break;
            case R.id.bt_sixin:
                break;
            case R.id.ll_xihuan:
                viewPager.setCurrentItem(0);

                break;
            case R.id.ll_tuijian:
                viewPager.setCurrentItem(1);

                break;
            case R.id.llguanzhu:
                viewPager.setCurrentItem(2);

                break;
            case R.id.ll_fensi:
                viewPager.setCurrentItem(3);

                break;
        }
    }
}
