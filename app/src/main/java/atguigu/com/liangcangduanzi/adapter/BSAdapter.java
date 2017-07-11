package atguigu.com.liangcangduanzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.fragment.bsFragment.DuanziFragment;
import atguigu.com.liangcangduanzi.fragment.bsFragment.TuiJianFragment;

/**
 * Created by ASUS on 2017/7/10.
 */

public class BSAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"推荐", "段子"};
    List<Fragment> fragments = new ArrayList<>();

    public BSAdapter(FragmentManager fm) {
        super(fm);
        initFragment();//设置数据到集合
    }

    private void initFragment() {
        fragments.add(new TuiJianFragment());
        fragments.add(new DuanziFragment());
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
