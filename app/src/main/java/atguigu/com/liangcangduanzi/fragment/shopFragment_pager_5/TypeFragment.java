package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.Pager_typeAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.TypeAllBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

public class TypeFragment extends BaseFragment {


    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;
    private List<TypeAllBean> typeAllBeen;
    private Pager_typeAdapter adapter;

    private  GridView gridView;



    @Override
    public View initView() {

        View view = View.inflate(getActivity(), R.layout.pager_type, null);
        ButterKnife.inject(this, view);

        gridView = pullToRefreshGridView.getRefreshableView();


        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    //设置监听
    private void initListener() {

        //adapter 中每个 图片的点击事件
        adapter.setOnItemClickListener(new Pager_typeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到 显示东西的 activity
            }
        });

        //先下刷新监听
        pullToRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override//下拉
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

                getDataFromNet();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                pullToRefreshGridView.onRefreshComplete();

            }
        });


    }

    //联网请求 数据
    private void getDataFromNet() {

        OkHttpUtils
                .get()
                .url(JieKouUtils.TYPE_ALL)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressData(response);

                    }
                });
    }


    private void progressData(String json) {
        Log.e("TAG", "json" + json);

        TypeAllBean typeAllBean = new Gson().fromJson(json, TypeAllBean.class);

        adapter = new Pager_typeAdapter(context);
        gridView.setAdapter(adapter);

        adapter.refresh(typeAllBean.getData().getItems());

        pullToRefreshGridView.onRefreshComplete();

        initListener();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}