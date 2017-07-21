package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.DaRenTuiJianPLAdapter;
import atguigu.com.liangcangduanzi.bean.DaRenTuiJianGoodsBean;
import atguigu.com.liangcangduanzi.bean.DaRenTuiJianPLBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import atguigu.com.liangcangduanzi.utils.PicassoImageLoader;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class DaRenRecommonPingLunInfoActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_name1)
    TextView tvName1;
    @InjectView(R.id.iv_like)
    ImageView ivLike;
    @InjectView(R.id.like_acount)
    TextView likeAcount;
    @InjectView(R.id.iv_image)
    ImageView ivImage;
    @InjectView(R.id.dutorName)
    TextView dutorName;
    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    @InjectView(R.id.activity_da_ren_recommon_goods_info)
    LinearLayout activityDaRenRecommonGoodsInfo;
    private String url1;
    private String url2;
    private DaRenTuiJianGoodsBean daRenTuiJianGoodsBean;

    private TextView tv_price;
    private DaRenTuiJianPLBean daRenTuiJianPLBean;
    private DaRenTuiJianPLAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_ren_recommon_goods_info);
        ButterKnife.inject(this);

        tv_price = (TextView)findViewById(R.id.tv_price);

        int id = getIntent().getIntExtra("id", -1);
        url1 = JieKouUtils.DRTJHEED + id + JieKouUtils.DRTJEND;

        Log.e("TAG", "URL1 == " + url1);

        url2 = JieKouUtils.DETJPLHEAD + id + JieKouUtils.DETJPLEND;
        Log.e("TAG", "URL2 == " + url2);
        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().get(url1, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    private void processData(String json) {
        daRenTuiJianGoodsBean = new Gson().fromJson(json, DaRenTuiJianGoodsBean.class);

        String goods_name = daRenTuiJianGoodsBean.getData().getItems().getGoods_name();
        String price = daRenTuiJianGoodsBean.getData().getItems().getPrice();
        String like_count = daRenTuiJianGoodsBean.getData().getItems().getLike_count();
        String headimg = daRenTuiJianGoodsBean.getData().getItems().getHeadimg();
        String owner_name = daRenTuiJianGoodsBean.getData().getItems().getOwner_name();

        tvName1.setText(goods_name);
        tv_price.setText("￥"+price);
        likeAcount.setText(like_count);
        dutorName.setText(owner_name);

        Picasso.with(this)
                .load(headimg)
                .into(ivImage);

        setBanner();
        initListener();

        initRecycleView();
    }

    // 初始化评论数据
    private void initRecycleView() {
       NetUtils.getInstance().get(url2, new NetUtils.OnOkHttpListener() {
           @Override
           public void onResponse(String response, int id) {
               processPingLunData(response);
           }

           @Override
           public void onError(Call call, Exception e, int id) {

           }
       });
    }

    private void processPingLunData(String json) {
        daRenTuiJianPLBean = new Gson().fromJson(json, DaRenTuiJianPLBean.class);

        adapter = new DaRenTuiJianPLAdapter(this,daRenTuiJianPLBean.getData().getItems());

        recycleview.setAdapter(adapter);
        recycleview.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initListener() {
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setBanner() {
        List<String> image = new ArrayList();
        if(daRenTuiJianGoodsBean.getData().getItems().getImages_item()!= null) {
            List<String> images_item = daRenTuiJianGoodsBean.getData().getItems().getImages_item();
            for(int i = 0; i < images_item.size(); i++) {
                image.add(images_item.get(i));
            }
            //设置图片加载器
            banner.setImageLoader(new PicassoImageLoader());
            //设置图片集合
            banner.setImages(image);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }

}
