package atguigu.com.bilibili.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import atguigu.com.bilibili.R;
import atguigu.com.bilibili.adapter.ZhiBoAdapter;
import atguigu.com.bilibili.base.BaseFragment;
import atguigu.com.bilibili.bean.ZhiBoBean;
import atguigu.com.bilibili.utils.AppNetManager;
import atguigu.com.bilibili.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/24.
 */

public class ZhiBoFragment extends BaseFragment {


    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;


    private ZhiBoBean zhiBoBean;
    private ZhiBoAdapter adapter;


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_zhibo, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().get(AppNetManager.LIVE_URL, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                processData(response);

                Log.e("TAG", AppNetManager.LIVE_URL);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    //解析数据
    private void processData(String json) {
        zhiBoBean = new Gson().fromJson(json, ZhiBoBean.class);
        //initBanner();

        adapter = new ZhiBoAdapter(context, zhiBoBean);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        setHeaderView(recyclerView);

    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(context).inflate(R.layout.headview, view, false);
        adapter.setHeaderView(header);
    }

    private List<String> image;

    /*private void initBanner() {
        List<ZhiBoBean.DataBean.BannerBean> banners = zhiBoBean.getData().getBanner();

        if (banners != null && banners.size() > 0) {
            image = new ArrayList();
            for (int i = 0; i < banners.size(); i++) {
                image.add(banners.get(i).getImg());
            }
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());     //设置图片集合
        banner.setImages(image);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



}
