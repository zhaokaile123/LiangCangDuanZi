package atguigu.com.liangcangduanzi.fragment.bsFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.RecyclerViewAdpater;
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


    @InjectView(R.id.recycleview)
    RecyclerView recycleview;

    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private BSTuiJianBean bsTuiJianBean;
    List<BSTuiJianBean.ListBean> list;

    private RecyclerViewAdpater adapter;

    private int i = 1;
    private boolean isLoadMore = false;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.bs_tuijian, null);
        ButterKnife.inject(this, view);

        refresh.setLoadMore(true);//支持上拉
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override// 下拉刷新
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = false;
                getDataFromNet();
            }

            @Override// 上拉加载
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = true;
                i++;
                Log.e("TAG","i="+i);
                getDataFromNet();
            }
        });


    }


    private void getDataFromNet() {
        NetUtils.getInstance().get(JieKouUtils.BSTUIJIANHEAD + 20 * i + JieKouUtils.BSTUIJIANEND, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {

                progressData(response);
                if (!isLoadMore) {
                    refresh.finishRefresh();
                } else {
                    refresh.finishRefreshLoadMore();
                }

                Log.e("TAG", "成功");
            }

            @Override
            public void onError(Call call, Exception e, int id) {

                Log.e("TAG", e.getMessage());
            }
        });
    }

    private void progressData(String json) {

        bsTuiJianBean = new Gson().fromJson(json, BSTuiJianBean.class);

        recycleview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        list = bsTuiJianBean.getList();

        if(!isLoadMore) {
            adapter = new RecyclerViewAdpater(context, list);

            recycleview.setAdapter(adapter);

        }else {

            adapter = new RecyclerViewAdpater(context, list);

            recycleview.setAdapter(adapter);

            recycleview.scrollToPosition((i-1)*20);
        }




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
