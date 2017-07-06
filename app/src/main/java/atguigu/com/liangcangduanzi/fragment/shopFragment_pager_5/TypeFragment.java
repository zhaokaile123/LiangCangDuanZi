package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;
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

    @InjectView(R.id.gridview)
    GridView gridview;

    private List<TypeAllBean> typeAllBeen ;
    private Pager_typeAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(getActivity(), R.layout.pager_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();

        initListener();
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

        //点击 搜索


        //点击购物车



    }

    //联网请求 数据
    private void getDataFromNet() {

        OkHttpUtils
                .get()
                .url(JieKouUtils.TYPE_ALL)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback()
                {
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
        Log.e("TAG","json" + json);

        TypeAllBean typeAllBean = new Gson().fromJson(json, TypeAllBean.class);

        adapter = new Pager_typeAdapter(context);
        gridview.setAdapter(adapter);

        adapter.refresh(typeAllBean.getData().getItems());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
