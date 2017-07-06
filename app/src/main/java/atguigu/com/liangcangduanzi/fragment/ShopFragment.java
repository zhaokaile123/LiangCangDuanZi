package atguigu.com.liangcangduanzi.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import atguigu.com.liangcangduanzi.R;
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
