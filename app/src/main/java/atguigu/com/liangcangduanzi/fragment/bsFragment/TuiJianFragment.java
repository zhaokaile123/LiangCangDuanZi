package atguigu.com.liangcangduanzi.fragment.bsFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.RecyclerViewAdpater;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.BSTuiJianBean;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/10.
 */

public class TuiJianFragment extends BaseFragment {

    @InjectView(R.id.recycleview)
    RecyclerView recycleview;

    private BSTuiJianBean bsTuiJianBean;

    private RecyclerViewAdpater adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.bs_tuijian, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {

        NetUtils.getInstance().get("http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json", new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                progressData(response);
                Log.e("TAG","成功");
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG",e.getMessage());

            }
        });
    }

    private void progressData(String json) {

        bsTuiJianBean = new Gson().fromJson(json, BSTuiJianBean.class);

        adapter = new RecyclerViewAdpater(context,bsTuiJianBean.getList());

        recycleview.setAdapter(adapter);

        recycleview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
