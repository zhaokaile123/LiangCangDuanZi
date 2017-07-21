package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.GoodsInfoBean1;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class DingDanActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_address)
    LinearLayout llAddress;
    @InjectView(R.id.iv_image)
    ImageView ivImage;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_shulaing)
    TextView tvShulaing;
    @InjectView(R.id.tv_accout)
    TextView tvAccout;
    @InjectView(R.id.ll_xiangqing)
    LinearLayout llXiangqing;
    @InjectView(R.id.youhuiquan)
    LinearLayout youhuiquan;
    @InjectView(R.id.beizhu)
    LinearLayout beizhu;
    @InjectView(R.id.down)
    ImageView down;
    @InjectView(R.id.ll_pay)
    LinearLayout llPay;
    @InjectView(R.id.wxpay)
    LinearLayout wxpay;
    @InjectView(R.id.alipay)
    LinearLayout alipay;
    @InjectView(R.id.ll_selector_pay)
    LinearLayout llSelectorPay;
    @InjectView(R.id.price_total)
    TextView priceTotal;
    @InjectView(R.id.price_discount)
    TextView priceDiscount;
    @InjectView(R.id.tv_pay)
    TextView tvPay;
    @InjectView(R.id.activity_ding_dan)
    RelativeLayout activityDingDan;

    @InjectView(R.id.tv_price)
    TextView tv_price;

    @InjectView(R.id.tv_price_all)
    TextView tv_price_all;

    @InjectView(R.id.tv_discount)
    TextView tv_discount;


    private ImageButton ib_wxpay;
    private ImageButton ib_alipay;
    private GoodsInfoBean1 goodsInfoBean1;
    private int aocunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ding_dan);
        ButterKnife.inject(this);

        ib_wxpay = (ImageButton)findViewById(R.id.ib_wxpay);
        ib_alipay = (ImageButton)findViewById(R.id.ib_alipay);

        getData();
        setData();
        initListener();
    }

    private void initListener() {
        llPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llSelectorPay.setVisibility(View.VISIBLE);
                down.setVisibility(View.GONE);
                llSelectorPay.setClickable(false);
            }
        });

        wxpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_wxpay.setImageResource(R.drawable.ic_payway_selected);
                ib_alipay.setImageResource(R.drawable.ic_payway_unselected);
            }
        });

        ib_wxpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_wxpay.setImageResource(R.drawable.ic_payway_selected);
                ib_alipay.setImageResource(R.drawable.ic_payway_unselected);
            }
        });


        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_alipay.setImageResource(R.drawable.ic_payway_selected);
                ib_wxpay.setImageResource(R.drawable.ic_payway_unselected);
            }
        });
        ib_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_alipay.setImageResource(R.drawable.ic_payway_selected);
                ib_wxpay.setImageResource(R.drawable.ic_payway_unselected);
            }
        });

    }

    private void setData() {
        String goods_image = goodsInfoBean1.getData().getItems().getGoods_image();
        Picasso.with(this)
                .load(goods_image)
                .into(ivImage);

        tvAccout.setText(aocunt+"");

        String price = goodsInfoBean1.getData().getItems().getPrice();

        tv_price.setText("￥" + price);
        Log.e("TAG",price);

        double allPrice = aocunt * Double.valueOf(price.toString());
        tv_price_all.setText("￥"+allPrice);

        //没有折扣
        if(TextUtils.isEmpty(goodsInfoBean1.getData().getItems().getDiscount_price())) {

            priceTotal.setText("￥"+allPrice);

        }else {

            String discount_price = goodsInfoBean1.getData().getItems().getDiscount_price();
            double discountAll = aocunt * Double.valueOf(discount_price.toString());

            double v = allPrice - discountAll;
            priceTotal.setText("￥"+ discountAll);

            tv_discount.setText("￥"+ v);
            priceDiscount.setText("已节省："+"￥"+ v);

        }
    }

    private void getData() {
        goodsInfoBean1 = (GoodsInfoBean1) getIntent().getSerializableExtra("goodsInfoBean1");
        aocunt = getIntent().getIntExtra("acount", -1);
    }
}
