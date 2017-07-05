package atguigu.com.liangcangduanzi.fragment;

import android.view.View;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.bean.BaseFragment;

/**
 * Created by ASUS on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {
    private TextView tv;
    @Override
    public View initView() {
        tv = new TextView(context);
        tv.setText(222+"");
        return tv;
    }
}
