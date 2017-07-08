package atguigu.com.liangcangduanzi.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.DarRenActivity;
import atguigu.com.liangcangduanzi.adapter.DarenAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.DarenBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {
    @InjectView(R.id.search)
    ImageView search;
    @InjectView(R.id.shoppingCar)
    ImageView shoppingCar;
    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;

    private GridView gridView;
    private DarenAdapter adapter;
    private List<DarenBean.DataBean.ItemsBean> items;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_daren, null);
        ButterKnife.inject(this, view);

        gridView = pullToRefreshGridView.getRefreshableView();
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
                .url(JieKouUtils.DAREN)
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
        DarenBean darenBean = new Gson().fromJson(json, DarenBean.class);

        items = darenBean.getData().getItems();

        adapter = new DarenAdapter(getActivity());

        gridView.setAdapter(adapter);

        adapter.refresh(items);

        initListener();

    }

    private void initListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),DarRenActivity.class);
                String username = items.get(i).getUsername();
                String duty = items.get(i).getDuty();
                String imageUrl = items.get(i).getUser_images().getOrig();

                intent.putExtra("username",username);
                intent.putExtra("duty",duty);
                intent.putExtra("imageUrl",imageUrl);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
