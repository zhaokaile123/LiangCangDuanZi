package atguigu.com.liangcangduanzi.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.BSAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/5.
 */

public class ShareFragment extends BaseFragment {

    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.baisi_share, null);
        ButterKnife.inject(this, view);

        //设置table Layout  与vapager 相互关联
        tablayout.setupWithViewPager(viewPager);
        //设置样式
        // tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合比较多的tab  可以滑动
        //tabMode 是不可以滑动   均分宽度  适合少量的tab 这中模式在布局中设置

        //  这个解决了 fragment 不显示的问题  用getChildFragmentManager()
        BSAdapter adapter = new BSAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

       /* viewPager.setCurrentItem(0);
        tablayout.getTabAt(0).select();*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
