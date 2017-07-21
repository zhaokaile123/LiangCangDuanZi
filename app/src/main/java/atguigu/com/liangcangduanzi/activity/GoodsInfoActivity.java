package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.GoodsInfoBean1;
import atguigu.com.liangcangduanzi.carstorage.ShoppingCarActivity;
import atguigu.com.liangcangduanzi.fragment.goodsInfoFragment_pager_2.GongGaoFragment;
import atguigu.com.liangcangduanzi.fragment.goodsInfoFragment_pager_2.XiangQingFragment;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import atguigu.com.liangcangduanzi.utils.PicassoImageLoader;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;


public class GoodsInfoActivity extends AppCompatActivity {

    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.iv_like)
    ImageView ivLike;
    @InjectView(R.id.like_acount)
    TextView likeAcount;
    @InjectView(R.id.tv_fenxiang)
    ImageView tvFenxiang;
    @InjectView(R.id.tv_money)
    TextView tvMoney;
    @InjectView(R.id.ll_goods_select)
    LinearLayout llGoodsSelect;
    @InjectView(R.id.product_image)
    ImageView productImage;
    @InjectView(R.id.product_brandName)
    TextView productBrandName;
    @InjectView(R.id.ll_product)
    LinearLayout llProduct;
    @InjectView(R.id.rb_good_xiangqing)
    RadioButton rbGoodXiangqing;
    @InjectView(R.id.rb_gouwuxuzhi)
    RadioButton rbGouwuxuzhi;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.shoppingCar)
    ImageView shoppingCar;
    @InjectView(R.id.iv_kefu)
    ImageView ivKefu;
    @InjectView(R.id.tv_addcar)
    TextView tvAddcar;
    @InjectView(R.id.tv_toby)
    TextView tvToby;
    @InjectView(R.id.activity_goods_info)
    RelativeLayout activityGoodsInfo;
    private RadioGroup rgMain;

    private int position;
    private Fragment tempFragment;


    private int id;

    public String getUrl() {
        return JieKouUtils.GOODSINFOHEAD + id + JieKouUtils.GOODSINFOEND;
    }

    private String url;
    private GoodsInfoBean1 goodsInfoBean1;

    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.inject(this);
        rgMain = (RadioGroup) findViewById(R.id.rg_main);


        id = getIntent().getIntExtra("id", -1);
        url = JieKouUtils.GOODSINFOHEAD + id + JieKouUtils.GOODSINFOEND;

        Log.e("TAG2","UTL" + url);

        getUrl();

        setDefult();

        getDataFromNet();

        initListener();
    }

    private void initListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //选择 尺码 颜色
        llGoodsSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsInfoActivity.this,GoodsSelectActivity.class);
                intent.putExtra("goodsInfoBean1",goodsInfoBean1);

                startActivity(intent);

            }
        });

        //点击 品牌 图片 汉字
        llProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GoodsInfoActivity.this, Brand_itemActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("image",goodsInfoBean1.getData().getItems().getHeadimg());
                intent.putExtra("brandname",goodsInfoBean1.getData().getItems().getBrand_info().getBrand_name());
                startActivity(intent);

            }
        });

        //点击购物车
        shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoodsInfoActivity.this,ShoppingCarActivity.class));
            }
        });
        //点击加入购物车
        tvAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GoodsInfoActivity.this,GoodsSelectActivity.class);
                intent.putExtra("goodsInfoBean1",goodsInfoBean1);

                startActivity(intent);
            }
        });

    }

    private void getDataFromNet() {
        NetUtils.getInstance().get(url, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                progressData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    private void progressData(String json) {
        goodsInfoBean1 = new Gson().fromJson(json, GoodsInfoBean1.class);
        setBanner();
        setData();
        setFragment();
    }
    //设置 点击 切换 Fragment
    private void setFragment() {
        initFragment();
        //设置RadioGroup的选中监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //设置默认选择 商品详情
        rgMain.check(R.id.rb_good_xiangqing);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new XiangQingFragment());
        fragments.add(new GongGaoFragment());
    }

    //设置名称 数据
    private void setData() {

        String headimg = goodsInfoBean1.getData().getItems().getHeadimg();
        //设置图片
        Picasso.with(this)
                .load(headimg)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(productImage);
        String owner_name = goodsInfoBean1.getData().getItems().getOwner_name();
        productBrandName.setText(owner_name);

        likeAcount.setText(goodsInfoBean1.getData().getItems().getLike_count());

        tvName.setText(goodsInfoBean1.getData().getItems().getBrand_info().getBrand_name());

        tvContent.setText(goodsInfoBean1.getData().getItems().getGoods_name());

        tvMoney.setText("￥" + goodsInfoBean1.getData().getItems().getPrice());

    }

    //设置banner 图片
    private void setBanner() {
        List<String> image = new ArrayList();
        if(goodsInfoBean1.getData().getItems().getImages_item()!= null) {
            List<String> images_item = goodsInfoBean1.getData().getItems().getImages_item();
            for(int i = 0; i < images_item.size(); i++) {
                image.add(images_item.get(i));
            }
            //设置图片加载器
            banner.setImageLoader(new PicassoImageLoader());
            //设置图片集合
            banner.setImages(image);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }

    }

    //rgMain 的 点击事件
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_good_xiangqing:
                    position = 0;
                    setDefult();
                    rbGoodXiangqing.setTextColor(Color.GRAY);
                    rbGoodXiangqing.setBackgroundColor(Color.WHITE);
                    break;
                case R.id.rb_gouwuxuzhi:
                    setDefult();
                    rbGouwuxuzhi.setTextColor(Color.GRAY);
                    rbGouwuxuzhi.setBackgroundColor(Color.WHITE);

                    position = 1;
                break;
            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }

    private void switchFragment(Fragment currentFragment) {
        if(currentFragment != tempFragment){//不是同一个
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!currentFragment.isAdded()){
                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.frameLayout,currentFragment);

            }else{
                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把当前的显示
                ft.show(currentFragment);
            }
            //提交
            ft.commit();
            tempFragment = currentFragment;
        }
    }
    private void setDefult() {

        rbGoodXiangqing.setTextColor(Color.WHITE);
        rbGoodXiangqing.setBackgroundColor(Color.GRAY);

        rbGouwuxuzhi.setTextColor(Color.WHITE);
        rbGouwuxuzhi.setBackgroundColor(Color.GRAY);
    }
}
