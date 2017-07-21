package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Brand_itemActivity;
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
    private List<BrandBean.DataBean.ItemsBean> items;


    private int i = 1;

    /**
     * 加载更多
     */
    private boolean refreshLoadMore = false;


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
                .url(JieKouUtils.BRANDHEAD + i + JieKouUtils.BRANDEND)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG5",""+i);
                        progressData(response);

                        pullRefreshList.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pullRefreshList.onRefreshComplete();
                            }
                        }, 1000);;//结束上拉刷新

                    }
                });

    }
    private void progressData(String json) {

        BrandBean brandBean = new Gson().fromJson(json, BrandBean.class);

        if(!refreshLoadMore) {
            items = brandBean.getData().getItems();

            if(items != null && items.size()>0) {

                adapter = new BrandAdapter(context,items);
                lv.setAdapter(adapter);

            }

        }else {  //加载更多
            List<BrandBean.DataBean.ItemsBean> data = brandBean.getData().getItems();

            items.addAll(data);
            adapter.notifyDataSetChanged();

        }

        initListener();
    }



    private void initListener() {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), Brand_itemActivity.class);
                intent.putExtra("id", items.get(i-1).getBrand_id());
                intent.putExtra("position",i-1);
                intent.putExtra("image",items.get(i-1).getBrand_logo());
                intent.putExtra("brandname",items.get(i-1).getBrand_name());
                startActivity(intent);
            }
        });

        pullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override// 下拉 刷新
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshLoadMore = false;
                i=1;
                getDataFromNet();
            }

            @Override// 上拉加载
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshLoadMore = true;
                i++;
                getDataFromNet();

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
