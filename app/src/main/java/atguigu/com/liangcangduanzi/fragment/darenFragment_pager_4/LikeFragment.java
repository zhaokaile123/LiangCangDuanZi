package atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.LikeFragmentAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.XiHuanBean;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/8.
 */

public class LikeFragment extends BaseFragment {


    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;


    private String url;
    private LikeFragmentAdapter adapter;

    private GridView gridView;
    private XiHuanBean xiHuanBean;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.daren_xihuan, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getUrl(url);

    }


    public void  getUrl(String url){
        this.url = url;

        if(!TextUtils.isEmpty(url)) {

            gridView = pullToRefreshGridView.getRefreshableView();
            Log.e("TAG","like url == " + url);

            getDataFromNet();
        }
    }


    private void getDataFromNet() {

        NetUtils.getInstance().get(url, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "ok " );
                progressData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "e = " + e.getMessage());

            }
        });
    }

    private void progressData(String json) {

        xiHuanBean = new Gson().fromJson(json, XiHuanBean.class);

        adapter = new LikeFragmentAdapter(context,xiHuanBean.getData().getItems().getGoods());


        //Log.e("TAG", "数据 = " + xiHuanBean.getData().getItems().getGoods().get(0).getGoods_name());

        gridView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
