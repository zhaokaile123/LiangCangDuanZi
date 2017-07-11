package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.Gift_itemsAdapter;
import atguigu.com.liangcangduanzi.bean.GiftAllBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class Gift_itemsActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.shoppingCar)
    ImageView shoppingCar;
    @InjectView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @InjectView(R.id.pullToRefreshGridView)

    PullToRefreshGridView pullToRefreshGridView;
    private String url;
    private Gift_itemsAdapter adapter;
    private GridView gridView;
    private GiftAllBean giftAllBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_items);
        ButterKnife.inject(this);
        url = getIntent().getStringExtra("url");


        gridView = pullToRefreshGridView.getRefreshableView();

        iniData();
    }

    private void iniData() {

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "shibai" + e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "成功");
                        progressData(response);

                    }
                });
    }

    private void progressData(String json) {
        giftAllBean = new Gson().fromJson(json, GiftAllBean.class);

        adapter = new Gift_itemsAdapter(this, giftAllBean.getData().getItems());

        gridView.setAdapter(adapter);
        pullToRefreshGridView.onRefreshComplete();

        iniListener();

    }

    private int id;
    private void iniListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                id = Integer.parseInt(giftAllBean.getData().getItems().get(i).getGoods_id());
                Intent intent = new Intent(Gift_itemsActivity.this,GoodsInfoActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }


}
