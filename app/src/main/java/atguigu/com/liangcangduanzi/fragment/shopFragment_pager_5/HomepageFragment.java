package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.view.View;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.base.BaseFragment;

/**
 * Created by ASUS on 2017/7/5.
 */

public class HomepageFragment extends BaseFragment {
    private TextView tv;
    @Override
    public View initView() {
        tv = new TextView(context);
        tv.setText(222+"");
        return tv;
    }
}
