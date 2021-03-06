package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.Type_itemsAdapter;
import atguigu.com.liangcangduanzi.bean.JiaJu1Bean;
import atguigu.com.liangcangduanzi.carstorage.ShoppingCarActivity;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class Type_itemsActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.shoppingCar)
    ImageView shoppingCar;
    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;
    private Type_itemsAdapter adapter;

    private GridView gridView;
    private String url;
    private JiaJu1Bean jiaJu1Bean;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jia_ju1);
        ButterKnife.inject(this);

        gridView = pullToRefreshGridView.getRefreshableView();

        url = getIntent().getStringExtra("url");
        Log.e("TAG","url22==" + url);

        initData();

    }

    private void initData() {

        getDataFromNet();
        initListener();
    }

    private void initListener() {
        //点击购物车
        shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Type_itemsActivity.this, ShoppingCarActivity.class));
            }
        });

        //返回
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击跳转 goodsInfoActivity 带着id 就行
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<JiaJu1Bean.DataBean.ItemsBean> items = jiaJu1Bean.getData().getItems();
                if(items!=null && items.size()>0) {
                    id = Integer.parseInt(items.get(i).getGoods_id());

                    Intent intent = new Intent(Type_itemsActivity.this,GoodsInfoActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });

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

        Log.e("TAG", "成功 == " + json);

        jiaJu1Bean = new Gson().fromJson(json, JiaJu1Bean.class);

        adapter = new Type_itemsAdapter(this, jiaJu1Bean.getData().getItems());

        gridView.setAdapter(adapter);

        pullToRefreshGridView.onRefreshComplete();

    }
}
