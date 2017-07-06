package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.HomeAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.HomeBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

public class HomepageFragment extends BaseFragment {

    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    private HomeAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.pager_home, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(JieKouUtils.HOME)
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

    List<HomeBean.DataBean.ItemsBean.ListBean> list;

    private void progressData(String json) {

        HomeBean homeBean = new Gson().fromJson(json, HomeBean.class);

        list = homeBean.getData().getItems().getList();

        adapter = new HomeAdapter(getActivity(),list);

        recycleview.setAdapter(adapter);

        recycleview.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
