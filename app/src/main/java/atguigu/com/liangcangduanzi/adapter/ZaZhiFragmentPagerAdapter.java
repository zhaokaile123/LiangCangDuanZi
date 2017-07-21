package atguigu.com.liangcangduanzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.fragment.zazhi_pager_fragment_2.ZaZhi_Autor_fragment;
import atguigu.com.liangcangduanzi.fragment.zazhi_pager_fragment_2.ZaZhi_type_fragment;

/**
 * Created by ASUS on 2017/7/13.
 */

public class ZaZhiFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"分类", "作者"};
    List<Fragment> fragments = new ArrayList<>();

    public ZaZhiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

        initFragment();//设置数据到集合
    }

    private void initFragment() {
        fragments.add(new ZaZhi_type_fragment());
        fragments.add(new ZaZhi_Autor_fragment());
    }

    @Override
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
