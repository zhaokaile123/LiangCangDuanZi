package atguigu.com.liangcangduanzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.fragment.bsFragment.TuiJianFragment;
import atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5.TypeFragment;

/**
 * Created by ASUS on 2017/7/10.
 */

public class BSTuiJianAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"推荐", "段子"};
    List<Fragment> fragments = new ArrayList<>();

    public BSTuiJianAdapter(FragmentManager fm) {
        super(fm);
        initFragment();//设置数据到集合
    }

    private void initFragment() {
        fragments.add(new TypeFragment());
        fragments.add(new TuiJianFragment());
    }

    @Override//给titlebar 设置数据
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
