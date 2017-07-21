package atguigu.com.liangcangduanzi.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Special_WebViewActivity;
import atguigu.com.liangcangduanzi.activity.ZaZhiActivity;
import atguigu.com.liangcangduanzi.adapter.ZaZhiAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.ZaZhiBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

public class ZaZhiFragment extends BaseFragment {



    @InjectView(R.id.tv_zazhi)
    TextView tvZazhi;
    @InjectView(R.id.rl_zazhi)
    RelativeLayout rlZazhi;
    @InjectView(R.id.recycleview)
    PullToRefreshRecyclerView recycleview;
    private ZaZhiAdapter adapter;

    private RecyclerView rc;
    private LinearLayoutManager linearLayoutManager;
    private int position;
    private TextSwitcher switcher;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_zazhi, null);
        ButterKnife.inject(this, view);
        rc = recycleview.getRecyclerView();
        switcher = (TextSwitcher) view.findViewById(R.id.tv_time);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                TextView tv = new TextView(context);
                tv.setTextColor(0xff0066FF);
                tv.setTextSize(20);
                tv.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
                return tv;

            }
        });


        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().get(JieKouUtils.ZAZHI, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });

    }

    private String[] keys;
    /**
     * 集合里边套着一个集合
     */
    private ArrayList<ArrayList<ZaZhiBean>> beans;

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

        adapter = new ZaZhiAdapter(context,beanitems);

        rc.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(context);

        rc.setLayoutManager(linearLayoutManager);

        initListener();

    }

    private void initListener() {

        rc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                position = linearLayoutManager.findFirstVisibleItemPosition();
                if(position == 0) {

                    switcher.setText("Today");

                }else {
                    showNext();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        adapter.setOnItemClickListener(new ZaZhiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String topic_url = beanitems.get(position).getTopic_url();
                String author_name = beanitems.get(position).getAuthor_name();
                Log.e("TAG","0000 == " + author_name);

                Intent intent = new Intent(context, Special_WebViewActivity.class);
                intent.putExtra("url",topic_url);
                intent.putExtra("author_name",author_name);

                Log.e("TAG","0000url == " + topic_url);

                startActivity(intent);

            }
        });

        rlZazhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ZaZhiActivity.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in_top,R.anim.anim_out_bottom);
            }
        });

    }

    public void showNext()
    {
       // String pro = beanitems.get(position ).getAddtime();
        String nexts = beanitems.get(position+1).getAddtime();

        String time = nexts.substring(0,10);

        switcher.setText(time);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.rl_zazhi)
    public void onViewClicked() {
    }
}
