package atguigu.com.liangcangduanzi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.SearchActivity;
import atguigu.com.liangcangduanzi.adapter.ShopFragmentAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/5.
 */

public class ShopFragment extends BaseFragment {


    @InjectView(R.id.search)
    ImageView search;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.shoppingCar)
    ImageView shoppingCar;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_shop, null);
        ButterKnife.inject(this, view);

        //设置table Layout  与vapager 相互关联
        tablayout.setupWithViewPager(viewPager);
        //设置样式
        // tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合比较多的tab  可以滑动
        //tabMode 是不可以滑动   均分宽度  适合少量的tab 这中模式在布局中设置

        ShopFragmentAdapter shopFragmentAdapter = new ShopFragmentAdapter(getFragmentManager());

        viewPager.setAdapter(shopFragmentAdapter);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        viewPager.setCurrentItem(2);
        tablayout.getTabAt(2).select();

        initListener();
    }

    private void initListener() {

        //点击 搜索

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        //点击购物车

        shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "我在shopFragment中，来设置我吧", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
