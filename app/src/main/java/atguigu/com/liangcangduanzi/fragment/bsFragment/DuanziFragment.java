package atguigu.com.liangcangduanzi.fragment.bsFragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.base.BaseFragment;

/**
 * Created by ASUS on 2017/7/11.
 */

public class DuanziFragment extends BaseFragment {

    TextView tv;
    @Override
    public View initView() {
        tv = new TextView(context);
        tv.setTextColor(Color.RED);
        tv.setText(154612+"");
        return tv;
    }
}
