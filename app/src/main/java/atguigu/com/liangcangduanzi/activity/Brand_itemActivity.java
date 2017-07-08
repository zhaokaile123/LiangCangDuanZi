package atguigu.com.liangcangduanzi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.Brand_ProductAdapter;
import atguigu.com.liangcangduanzi.fragment.brand_produact.ProductFragment;
import atguigu.com.liangcangduanzi.fragment.brand_produact.StoryFragment;
import atguigu.com.liangcangduanzi.utils.CircleTransform;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Brand_itemActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.brand_name)
    TextView brandName;
    @InjectView(R.id.story)
    TextView story;
    @InjectView(R.id.product)
    TextView product;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private int id ;
    private String imageUrl;
    private String brandname;


    public String url;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_item);
        ButterKnife.inject(this);

        id = getIntent().getIntExtra("id",-1);
        Log.e("TAG","id == "+ id);
        imageUrl = getIntent().getStringExtra("image");
        brandname = getIntent().getStringExtra("brandname");
        position = getIntent().getIntExtra("position",-1);

        initFragment();
        selectViewpager(1);
        viewPager.setCurrentItem(1);
        initListener();

        initData();
    }

    private void initData() {

        //设置头部图片    picasso 加载圆形图片
        Picasso.with(this)
                .load(imageUrl)
                .transform(new CircleTransform())
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(ivLogo);

        //设置 品牌名
        brandName.setText(brandname);

        // 头+ id + 尾 就是 品牌产品的信息
        url = JieKouUtils.HEAD + id + JieKouUtils.END;
        Log.e("TAG","url == " + url);

    }

    private void setDefult() {

        story.setTextColor(Color.WHITE);
        story.setBackgroundColor(Color.GRAY);

        product.setTextColor(Color.WHITE);
        product.setBackgroundColor(Color.GRAY);
    }

    private void initFragment() {
        fragments =  new ArrayList<>();
        fragments.add(new StoryFragment());
        fragments.add(new ProductFragment());

        Brand_ProductAdapter adapter = new Brand_ProductAdapter(getSupportFragmentManager(),fragments);

        viewPager.setAdapter(adapter);
    }

    private void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectViewpager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void selectViewpager(int position) {
        switch (position){
            case 0:
                setDefult();

                story.setTextColor(Color.GRAY);
                story.setBackgroundColor(Color.WHITE);

                break;
            case 1:
                setDefult();

                product.setTextColor(Color.GRAY);
                product.setBackgroundColor(Color.WHITE);

                break;
        }

    }

    @OnClick({R.id.iv_back, R.id.story, R.id.product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.story:
                viewPager.setCurrentItem(0);

                selectViewpager(viewPager.getCurrentItem());


                break;
            case R.id.product:

                viewPager.setCurrentItem(1);
                selectViewpager(viewPager.getCurrentItem());

                break;
        }
    }
}
