package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.BaseFragment;
import atguigu.com.liangcangduanzi.fragment.DaRenFragment;
import atguigu.com.liangcangduanzi.fragment.Pesonal;
import atguigu.com.liangcangduanzi.fragment.ShareFragment;
import atguigu.com.liangcangduanzi.fragment.ShopFragment;
import atguigu.com.liangcangduanzi.fragment.ZaZhiFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_type)
    RadioButton rbType;
    @InjectView(R.id.rb_community)
    RadioButton rbCommunity;
    @InjectView(R.id.rb_cart)
    RadioButton rbCart;
    @InjectView(R.id.rb_user)
    RadioButton rbUser;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    /**
     * Fragment的集合
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * 选择某个Fragment的位置
     */
    private int position = 0;
    /**
     * 之前显示过的Fragment
     */
    private Fragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //初始多个页面对应的Fragment并且设置默认的Fragment页面
        initFragment();
        //设置RadioGroup的选中监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //设置默认选择首页
        rgMain.check(R.id.rb_home);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new ZaZhiFragment());
        fragments.add(new DaRenFragment());
        fragments.add(new ShareFragment());
        fragments.add(new Pesonal());
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_type:
                    position = 1;
                    break;
                case R.id.rb_community:
                    position = 2;
                    break;
                case R.id.rb_cart:
                    position = 3;
                    break;
                case R.id.rb_user:
                    position = 4;
                    break;

            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }

    /**
     * 要显示的Fragment
     * @param currentFragment
     */
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
}
