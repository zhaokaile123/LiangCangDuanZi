package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.BrandAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.BrandBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

//品牌  的 界面
public class BrandFragment extends BaseFragment {
    @InjectView(R.id.list)
    PullToRefreshListView pullRefreshList;

    private ListView lv;

    private BrandAdapter adapter;


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.pager_brand, null);
        ButterKnife.inject(this, view);

        lv = pullRefreshList.getRefreshableView();
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
                .url(JieKouUtils.BRAND)
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

        BrandBean brandBean = new Gson().fromJson(json, BrandBean.class);
        List<BrandBean.DataBean.ItemsBean> items = brandBean.getData().getItems();

        adapter = new BrandAdapter(context);

        lv.setAdapter(adapter);

        adapter.refresh(items);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
