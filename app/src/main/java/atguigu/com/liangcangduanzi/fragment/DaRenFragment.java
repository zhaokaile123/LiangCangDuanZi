package atguigu.com.liangcangduanzi.fragment;

import android.view.View;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.base.BaseFragment;

/**
 * Created by ASUS on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {
    private TextView tv;
    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.fragment_daren,null);
        return tv;
    }
}
