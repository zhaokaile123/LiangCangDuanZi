package atguigu.com.liangcangduanzi.fragment.bsFragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.BSTuiJianBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/10.
 */

public class TuiJianFragment extends BaseFragment {


    @InjectView(R.id.list)
    PullToRefreshListView list;
    private ListView listView;
    private BSTuiJianBean bsTuiJianBean;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.bs_tuijian, null);
        ButterKnife.inject(this, view);
        listView = list.getRefreshableView();

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().get(JieKouUtils.BSTUIJIAN, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                progressData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    private void progressData(String json) {
        bsTuiJianBean = new Gson().fromJson(json, BSTuiJianBean.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
