package atguigu.com.liangcangduanzi.fragment.goodsInfoFragment_pager_2;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.GoodsInfoActivity;
import atguigu.com.liangcangduanzi.adapter.ShangPinXiangQingAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.GoodsInfoBean1;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/10.
 */

public class XiangQingFragment extends BaseFragment {

    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    private String url;
    private GoodsInfoBean1 goodsInfoBean1;
    private ShangPinXiangQingAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_xiangqing, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        GoodsInfoActivity activity = (GoodsInfoActivity) getActivity();
        url = activity.getUrl();

        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().get(url, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                progressData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    private void progressData(String json) {
        goodsInfoBean1 = new Gson().fromJson(json, GoodsInfoBean1.class);
        adapter = new ShangPinXiangQingAdapter(context,goodsInfoBean1.getData().getItems());
        recycleview.setAdapter(adapter);

        recycleview.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
