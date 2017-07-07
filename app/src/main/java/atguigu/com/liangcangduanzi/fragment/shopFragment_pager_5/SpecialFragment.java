package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Special_WebViewActivity;
import atguigu.com.liangcangduanzi.adapter.Pager_typeAdapter;
import atguigu.com.liangcangduanzi.adapter.SpecialAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.SpecialBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

public class SpecialFragment extends BaseFragment {

    @InjectView(R.id.list)
    PullToRefreshListView list;

    private ListView listview;
    private SpecialAdapter adapter;
    private SpecialBean specialBean;

    @Override
    public View initView() {

        View viwe = View.inflate(context, R.layout.pager_special, null);
        ButterKnife.inject(this, viwe);
        listview = list.getRefreshableView();
        return viwe;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();


    }

    private void initListener() {

        adapter.setOnItemClickListener(new Pager_typeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = specialBean.getData().getItems().get(position).getTopic_url();
                String content = specialBean.getData().getItems().get(position).getTopic_name();
                Intent intent = new Intent(getActivity(), Special_WebViewActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("content",content);
                startActivity(intent);

            }
        });

    }

    private void getDataFromNet() {

        OkHttpUtils
                .get()
                .url(JieKouUtils.ZHUANTI)
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

        specialBean = new Gson().fromJson(json, SpecialBean.class);

        adapter = new SpecialAdapter(getActivity());

        listview.setAdapter(adapter);

        adapter.refresh(specialBean.getData().getItems());

        initListener();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
