package atguigu.com.liangcangduanzi.fragment.brand_produact;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Brand_itemActivity;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.ProductBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/7.
 */

public class StoryFragment extends BaseFragment {


    @InjectView(R.id.tv_content)
    TextView tvContent;
    private String url;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.brand_story, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        Brand_itemActivity activity = (Brand_itemActivity) getActivity();
        url = activity.url;


        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
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


        if(productBean.getData().getItems().size() > 0) {
            tvContent.setText(productBean.getData().getItems().get(0).getBrand_info().getBrand_desc());
        }else {
            tvContent.setText("暂无简介");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
