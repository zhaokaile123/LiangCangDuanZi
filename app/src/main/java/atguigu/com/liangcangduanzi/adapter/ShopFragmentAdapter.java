package atguigu.com.liangcangduanzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5.BrandFragment;
import atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5.GiftFragment;
import atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5.HomepageFragment;
import atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5.SpecialFragment;
import atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5.TypeFragment;

/**
 * Created by ASUS on 2017/7/5.
 */

public class ShopFragmentAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"分类", "品牌","首页","专题","礼物"};
    //创建一个fragment 的集合 把这5个fragment 添加进来
    List<Fragment> fragments = new ArrayList<>();

    @Override//给titlebar 设置数据
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public ShopFragmentAdapter(FragmentManager fm) {
        super(fm);

        initFragment();//设置数据到集合
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new TypeFragment());
        fragments.add(new BrandFragment());
        fragments.add(new HomepageFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new GiftFragment());
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
