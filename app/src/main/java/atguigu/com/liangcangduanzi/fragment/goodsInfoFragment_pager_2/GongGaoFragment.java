package atguigu.com.liangcangduanzi.fragment.goodsInfoFragment_pager_2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.ShouHouActivity;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/10.
 */

public class GongGaoFragment extends BaseFragment {

    @InjectView(R.id.bt_shouhou)
    Button btShouhou;
    private TextView tv;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fagmentz_xuzhi, null);
        ButterKnife.inject(this, view);
        tv = (TextView) view.findViewById(R.id.tv);

        tv.setText("所有商品均为正品保证。\n" +
                "中国大陆地区免运费，默认商家合作快递。\n" +
                "蜡烛，液态品，手表等凡萨蒂啊d啥大叔大叔大叔的啊大S大声地说阿达是多少。\n" +
                "的撒旦撒旦啊dasdasdasdsad按时手动asd啥。");

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initListener();
    }

    private void initListener() {
        btShouhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShouHouActivity.class));

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
