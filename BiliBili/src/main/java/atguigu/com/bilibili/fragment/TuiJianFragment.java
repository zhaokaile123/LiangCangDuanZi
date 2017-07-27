package atguigu.com.bilibili.fragment;


import android.view.View;
import android.widget.TextView;

import atguigu.com.bilibili.base.BaseFragment;

/**
 * Created by ASUS on 2017/7/24.
 */

public class TuiJianFragment extends BaseFragment {

    private TextView tv;
    @Override
    public View initView() {

        tv = new TextView(context);
        tv.setText(156312+"");
        return tv;
    }
}
