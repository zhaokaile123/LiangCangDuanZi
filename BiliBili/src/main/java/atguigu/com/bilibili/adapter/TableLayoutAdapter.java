package atguigu.com.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.bilibili.fragment.DongTaiFragment;
import atguigu.com.bilibili.fragment.FenQuFragment;
import atguigu.com.bilibili.fragment.TuiJianFragment;
import atguigu.com.bilibili.fragment.ZhiBoFragment;
import atguigu.com.bilibili.fragment.ZhuiFanFragment;

/**
 * Created by ASUS on 2017/7/24.
 */

public class TableLayoutAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"直播", "推荐","追番","分区","动态"};
    //创建一个fragment 的集合 把这5个fragment 添加进来
    List<Fragment> fragments = new ArrayList<>();



    @Override//给titlebar 设置数据
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public TableLayoutAdapter(FragmentManager fm) {
        super(fm);
        initFragment();//设置数据到集合
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new ZhiBoFragment());
        fragments.add(new TuiJianFragment());
        fragments.add(new ZhuiFanFragment());
        fragments.add(new FenQuFragment());
        fragments.add(new DongTaiFragment());
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