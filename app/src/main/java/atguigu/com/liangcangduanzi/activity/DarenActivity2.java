package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.DarenAdapter;
import atguigu.com.liangcangduanzi.bean.DarenBean;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class DarenActivity2 extends AppCompatActivity {

    @InjectView(R.id.search)
    ImageView search;
    @InjectView(R.id.shoppingCar)
    ImageView shoppingCar;
    @InjectView(R.id.pullToRefreshGridView)
    PullToRefreshGridView pullToRefreshGridView;
    private String url;

    private GridView gridView;
    private DarenAdapter adapter;
    private List<DarenBean.DataBean.ItemsBean> items;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_daren);
        ButterKnife.inject(this);

        initData();
    }

    private void initData() {
        gridView = pullToRefreshGridView.getRefreshableView();
        url = getIntent().getStringExtra("url");

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
        DarenBean darenBean = new Gson().fromJson(json, DarenBean.class);

        items = darenBean.getData().getItems();

        adapter = new DarenAdapter(this);

        gridView.setAdapter(adapter);

        adapter.refresh(items);

        initListener();

    }
    private void initListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DarenActivity2.this,DarRenActivity.class);
                String username = items.get(i).getUsername();
                String duty = items.get(i).getDuty();
                String imageUrl = items.get(i).getUser_images().getOrig();
                uid = Integer.parseInt(items.get(i).getUid());

                intent.putExtra("username",username);
                intent.putExtra("duty",duty);
                intent.putExtra("imageUrl",imageUrl);
                intent.putExtra("uid",uid);

                startActivity(intent);
            }
        });

        //点击排序 按钮
        shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DarenActivity2.this, PaiXuActivity.class));
            }
        });

    }
}
