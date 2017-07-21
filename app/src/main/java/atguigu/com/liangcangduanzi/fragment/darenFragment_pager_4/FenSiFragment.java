package atguigu.com.liangcangduanzi.fragment.darenFragment_pager_4;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.DarRenActivity;
import atguigu.com.liangcangduanzi.adapter.GuanZhuFenSiAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.GuanZhuFenSiBean;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/8.
 */

public class FenSiFragment extends BaseFragment {

    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;


    private String url;
    private GuanZhuFenSiAdapter adapter;

    private GridView gridView;

    private GuanZhuFenSiBean guanZhuFenSiBean;
    private int uid;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.daren_guanzhu, null);
        ButterKnife.inject(this, view);
        return view;

    }

    public void  getUrl(String url){
        this.url = url;

        if(!TextUtils.isEmpty(url)) {
            gridView = pullToRefreshGridView.getRefreshableView();

            getDataFromNet();
        }
    }
    @Override
    public void initData() {
        super.initData();
        getUrl(url);
    }

    private void getDataFromNet() {

        NetUtils.getInstance().get(url, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {

                progressData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "e = " + e.getMessage());

            }
        });
    }

    private void progressData(String json) {

        guanZhuFenSiBean = new Gson().fromJson(json, GuanZhuFenSiBean.class);

        adapter = new GuanZhuFenSiAdapter(context,guanZhuFenSiBean.getData().getItems().getUsers());


        gridView.setAdapter(adapter);

        iniListener();

    }

    private void iniListener() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(context,DarRenActivity.class);
                String username = guanZhuFenSiBean.getData().getItems().getUsers().get(i).getUser_name();
                String duty = guanZhuFenSiBean.getData().getItems().getUsers().get(i).getUser_desc();
                String imageUrl = guanZhuFenSiBean.getData().getItems().getUsers().get(i).getUser_image().getOrig();
                uid = Integer.parseInt(guanZhuFenSiBean.getData().getItems().getUsers().get(i).getUser_id());

                intent.putExtra("username",username);
                intent.putExtra("duty",duty);
                intent.putExtra("imageUrl",imageUrl);
                intent.putExtra("uid",uid);

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
