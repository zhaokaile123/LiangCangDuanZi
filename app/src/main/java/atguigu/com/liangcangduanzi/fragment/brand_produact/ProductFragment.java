package atguigu.com.liangcangduanzi.fragment.brand_produact;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Brand_itemActivity;
import atguigu.com.liangcangduanzi.activity.GoodsInfoActivity;
import atguigu.com.liangcangduanzi.adapter.ProductAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.ProductBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/7.
 */

public class ProductFragment extends BaseFragment {

    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;

    private String url;
    private GridView gridview;
    private ProductAdapter adapter;


    private List<ProductBean.DataBean.ItemsBean> items;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.brand_product, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        gridview = pullToRefreshGridView.getRefreshableView();

        Brand_itemActivity activity = (Brand_itemActivity) getActivity();
        url = activity.url;
        adapter = new ProductAdapter(getActivity());

        getDataFromNet();
        initListener();

    }

    //点击跳转 ingfoActivity
    private void initListener() {

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                if(items!= null && items.size() > 0) {
                    int id = Integer.parseInt(items.get(i).getGoods_id());
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });

    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(url)
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

        ProductBean productBean = new Gson().fromJson(json, ProductBean.class);

        items = productBean.getData().getItems();

        if (items != null && items.size() > 0) {

            gridview.setAdapter(adapter);
            adapter.refresh(items);

        } else {

        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
