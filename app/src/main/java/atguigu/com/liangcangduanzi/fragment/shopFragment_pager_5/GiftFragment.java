package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Gift_itemsActivity;
import atguigu.com.liangcangduanzi.activity.LoginActiviy;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ASUS on 2017/7/5.
 */

public class GiftFragment extends BaseFragment {

    @InjectView(R.id.iv_jingxuan)
    LinearLayout ivJingxuan;
    @InjectView(R.id.iv_jieri)
    LinearLayout ivJieri;
    @InjectView(R.id.iv_aiqing)
    LinearLayout ivAiqing;
    @InjectView(R.id.iv_shengri)
    LinearLayout ivShengri;
    @InjectView(R.id.iv_pengyou)
    LinearLayout ivPengyou;
    @InjectView(R.id.iv_haizi)
    LinearLayout ivHaizi;
    @InjectView(R.id.iv_fumu)
    LinearLayout ivFumu;
    @InjectView(R.id.iv_tixing)
    LinearLayout ivTixing;

    private String url;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.pager_gift, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_jingxuan, R.id.iv_jieri, R.id.iv_aiqing, R.id.iv_shengri,
              R.id.iv_pengyou, R.id.iv_haizi, R.id.iv_fumu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_jingxuan:
                url = JieKouUtils.JINGXUAN;
                break;
            case R.id.iv_jieri:
                url = JieKouUtils.JIERI;
                break;
            case R.id.iv_aiqing:
                url = JieKouUtils.AIQING;
                break;
            case R.id.iv_shengri:
                url = JieKouUtils.SHENGTI;
                break;
            case R.id.iv_pengyou:
                url = JieKouUtils.PENGYOU;
                break;
            case R.id.iv_haizi:
                url = JieKouUtils.HAIZI;
                break;
            case R.id.iv_fumu:
                url = JieKouUtils.FUMU;
                break;
        }

        Intent intent = new Intent(getActivity(), Gift_itemsActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void initData() {
        super.initData();

        ivTixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, LoginActiviy.class));
            }
        });
    }
}
