package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.ZaZhiItemAdapter;
import atguigu.com.liangcangduanzi.bean.ZaZhiBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class ZaZhiItemActivity extends AppCompatActivity {

    @InjectView(R.id.pull_to_refresh_recycleView)
    PullToRefreshRecyclerView pullToRefreshRecycleView;

    private RecyclerView recyclerView;

    private int id;
    private ZaZhiItemAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView tv_zazhi;
    private ImageView iv_back;
    private LinearLayout ll_tou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_za_zhi_item);
        ButterKnife.inject(this);

        recyclerView = pullToRefreshRecycleView.getRecyclerView();
        tv_zazhi = (TextView)findViewById(R.id.tv_zazhi);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ll_tou = (LinearLayout) findViewById(R.id.ll_tou);


        id = getIntent().getIntExtra("author_id",-1);

        initData();
    }

    private void initData() {

        String tv_zazhisss = getIntent().getStringExtra("author_name");
        tv_zazhi.setText(tv_zazhisss);

        getDataFromNet();
    }

    private void getDataFromNet() {

        String url = JieKouUtils.AUTORITEM_HEND + id + JieKouUtils.AUTORITEM_END;


        NetUtils.getInstance().get(url, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    private ArrayList<ArrayList<ZaZhiBean>> beans;
    private String[] keys;
    private ArrayList<ZaZhiBean> beanitems;

    private void processData(String json) {

        beans = new ArrayList<>();
        beanitems = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            if (object != null) {
                JSONObject data = object.optJSONObject("data");
                if (data != null) {
                    JSONObject items = data.optJSONObject("items");
                    if (items != null) {
                        JSONArray arrs = items.optJSONArray("keys");
                        if (arrs != null && arrs.length() > 0) {
                            keys = new String[arrs.length()];
                            for (int i = 0; i < arrs.length(); i++) {
                                keys[i] = (String) arrs.opt(i);
                            }
                            JSONObject infos = items.optJSONObject("infos");
                            for (int i = 0; i < keys.length; i++) {
                                JSONArray jsonArray = infos.optJSONArray(keys[i]);
                                ArrayList<ZaZhiBean> list = new ArrayList<>();
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(j);
                                    ZaZhiBean productionBean = new ZaZhiBean();

                                    productionBean.setAccess_url(jsonObject.optString("access_url"));
                                    productionBean.setTaid(jsonObject.optString("taid"));
                                    productionBean.setTopic_name(jsonObject.optString("topic_name"));
                                    productionBean.setCat_id(jsonObject.optString("cat_id"));
                                    productionBean.setAuthor_id(jsonObject.optString("author_id"));
                                    productionBean.setTopic_url(jsonObject.optString("topic_url"));
                                    productionBean.setCover_img(jsonObject.optString("cover_img"));
                                    productionBean.setCover_img_new(jsonObject.optString("cover_img_new"));
                                    productionBean.setHit_number(jsonObject.optInt("hit_number"));
                                    productionBean.setAddtime(jsonObject.optString("addtime"));
                                    productionBean.setContent(jsonObject.optString("content"));
                                    productionBean.setNav_title(jsonObject.optString("nav_title"));
                                    productionBean.setAuthor_name(jsonObject.optString("author_name"));
                                    productionBean.setCat_name(jsonObject.optString("cat_name"));

                                    list.add(productionBean);
                                }
                                if (list != null && list.size() > 0) {
                                    beans.add(list);
                                    beanitems.addAll(list);

                                }
                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 解析数据成功后 设置 数据
         */

        setData();

    }

    private void setData() {


        adapter = new ZaZhiItemAdapter(ZaZhiItemActivity.this,beanitems);

        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        initListener();
    }

    private void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter.setOnItemClickListener(new ZaZhiItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String topic_url = beanitems.get(position).getTopic_url();
                String author_name = beanitems.get(position).getAuthor_name();

                Intent intent = new Intent(ZaZhiItemActivity.this, Special_WebViewActivity.class);
                intent.putExtra("url",topic_url);
                intent.putExtra("author_name",author_name);
                startActivity(intent);
                finish();
            }
        });

        //点击黑色布局
        ll_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }
}
