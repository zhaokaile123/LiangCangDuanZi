package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.GoodsInfoBean1;
import atguigu.com.liangcangduanzi.carstorage.CartStorage;
import atguigu.com.liangcangduanzi.carstorage.ShoppingCarBean;
import atguigu.com.liangcangduanzi.view.AddSubView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import static atguigu.com.liangcangduanzi.R.id.tv_addcar;

public class GoodsSelectActivity extends AppCompatActivity {


    @InjectView(R.id.iv_goodsImage)
    ImageView ivGoodsImage;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.tv_money)
    TextView tvMoney;
    @InjectView(R.id.tv_type)
    TextView tvType;
    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @InjectView(R.id.tv_type2)
    TextView tvType2;
    @InjectView(R.id.id_flowlayout2)
    TagFlowLayout idFlowlayout2;
    @InjectView(R.id.tv_shuliang)
    TextView tvShuliang;
    @InjectView(R.id.add_sub_view)
    AddSubView addSubView;
    @InjectView(R.id.tv_addcar)
    TextView tvAddcar;
    @InjectView(R.id.tv_toby)
    TextView tvToby;
    @InjectView(R.id.activity_goods_select)
    RelativeLayout activityGoodsSelect;

    private TextView tv_content;
    private TextView tv_name;
    private TextView tv_addCar;
    private TextView tv_toby;

    private View tv;
    private View tv1;

    private GoodsInfoBean1 goodsInfoBean1;
    private List<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean> sku_info;

    private CartStorage storage;

    private int position1;
    private int position2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_select);
        ButterKnife.inject(this);

        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_name = (TextView) findViewById(R.id.tv_name);

        tv_addCar = (TextView) findViewById(tv_addcar);
        tv_toby = (TextView) findViewById(R.id.tv_toby);

        storage = CartStorage.getInstance(this);

        goodsInfoBean1 = (GoodsInfoBean1) getIntent().getSerializableExtra("goodsInfoBean1");
        setAddSubView();

        getData1();
        getData2();

        setListener();
    }

    private void setPrice() {
        String attr_id1 = goodsInfoBean1.getData().getItems().getSku_info().get(0).getAttrList().get(position1).getAttr_id();
        String attr_id2 = goodsInfoBean1.getData().getItems().getSku_info().get(1).getAttrList().get(position2).getAttr_id();

        String str = (attr_id1 + "," + attr_id2).toString().trim();


        List<GoodsInfoBean1.DataBean.ItemsBean.SkuInvBean> sku_inv = goodsInfoBean1.getData().getItems().getSku_inv();
        Log.e("TAG", "str == " + str);

        for (int i = 0; i < sku_inv.size(); i++) {
            if (str.equals(sku_inv.get(i).getAttr_keys().toString().trim())) {

                Log.e("TAG", "sku_inv.get(i).getAttr_keys().toString().trim() == " + sku_inv.get(i).getAttr_keys().toString().trim());

                tvMoney.setText("￥" + sku_inv.get(i).getPrice());
            }
        }
    }

    private void getData2(){
        // 第二个集合的
        if (sku_info.size() > 1) {

            tvType2.setVisibility(View.VISIBLE);

            List<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList2 = sku_info.get(1).getAttrList();
            tvType2.setText(sku_info.get(1).getType_name());

            String[] names2 = new String[attrList2.size()];

            for (int i = 0; i < attrList2.size(); i++) {

                names2[i] = attrList2.get(i).getAttr_name();
            }

            MyAdapter adapter = new MyAdapter(attrList2);
            idFlowlayout2.setAdapter(adapter);



            idFlowlayout2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {

                    position2 = position;

                    Log.e("TAG", "2 ==  " + position2);

                    tvMoney.setText("");
                    setPrice();

                    view.setBackgroundColor(Color.parseColor("#00Bfff"));

                    if (tv1 == view) {

                        idFlowlayout2.getChildAt(position).setClickable(false);
                        return true;

                    }
                    if (tv1 != null) {

                        tv1.setBackgroundColor(Color.parseColor("#808080"));
                    }

                    tv1 = view;
                    return true;
                }
            });


        }

    }
    class MyAdapter extends TagAdapter<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean.AttrListBean> {


        private final List<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean.AttrListBean> datas;

        public MyAdapter(List<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean.AttrListBean> datas) {
            super(datas);
            this.datas = datas;
        }

        @Override
        public View getView(FlowLayout parent, int position, GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean.AttrListBean attrListBean) {
            TextView tv = new TextView(GoodsSelectActivity.this);

            tv.setTextSize(20);

            tv.setHeight(80);
            tv.setPadding(20, 10, 20, 10);

            Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
            tv.setBackgroundDrawable(drawable);

            tv.setGravity(Gravity.CENTER);

            tv.setTextColor(Color.WHITE);
            tv.setText(datas.get(position).getAttr_name());

            return tv;
        }
    }



    private void setListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //添加 到 购物车
        tv_addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingCarBean shoppingCarBean = new ShoppingCarBean();

                //价格
                String price = tvMoney.getText().toString();
                shoppingCarBean.setPrice(price);
                //名字
                String goods_name = goodsInfoBean1.getData().getItems().getGoods_name();
                shoppingCarBean.setName(goods_name);

                //  id
                List<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean> sku_info = goodsInfoBean1.getData().getItems().getSku_info();
                if (sku_info.size() <= 1) {
                    String str1 = goodsInfoBean1.getData().getItems().getGoods_id().toString().trim();
                    String str2 = goodsInfoBean1.getData().getItems().getSku_info().get(0).getAttrList().get(position1).getAttr_id().toString().trim();

                    shoppingCarBean.setId(str1 + str2);
                } else {
                    String attr_id1 = goodsInfoBean1.getData().getItems().getSku_info().get(0).getAttrList().get(position1).getAttr_id();
                    String attr_id2 = goodsInfoBean1.getData().getItems().getSku_info().get(1).getAttrList().get(position2).getAttr_id();

                    String str1 = (attr_id1 + attr_id2).toString().trim();
                    String str2 = goodsInfoBean1.getData().getItems().getGoods_id().toString().trim();

                    shoppingCarBean.setId(str1 + str2);
                }

                //数量
                int number = addSubView.getValue();
                Log.e("TAG", "shuliang == " + number);
                shoppingCarBean.setNumb(number);

                //折扣价格
                String discount_price = goodsInfoBean1.getData().getItems().getDiscount_price();

                if (TextUtils.isEmpty(discount_price)) {
                    shoppingCarBean.setDiscount(0.00 + "");
                } else {

                    double discount = Double.valueOf(price.toString().trim().substring(1)) - Double.valueOf(discount_price.toString().trim());

                    double v = discount * number;
                    Log.e("TAG","jiesheng == " + v);
                    shoppingCarBean.setDiscount(v + "");

                }


                //图片
                String goods_image = goodsInfoBean1.getData().getItems().getGoods_image();

                Log.e("TAG", "tupian == " + goods_image);
                shoppingCarBean.setImageUrl(goods_image);



                //品牌名
                String brand_name = goodsInfoBean1.getData().getItems().getBrand_info().getBrand_name();
                shoppingCarBean.setBrandname(brand_name);


                storage.addData(shoppingCarBean);
                finish();

                Toast.makeText(GoodsSelectActivity.this, "已经添加到购物车", Toast.LENGTH_SHORT).show();

            }
        });

        //确认购买
        tv_toby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsSelectActivity.this, DingDanActivity.class);
                intent.putExtra("goodsInfoBean1", goodsInfoBean1);
                intent.putExtra("acount", addSubView.getValue());
                Log.e("TAG", addSubView.getValue() + "");
                startActivity(intent);
            }
        });


    }

    private void setAddSubView() {

        addSubView.setMaxValue(1000);
        addSubView.setMinValue(0);

    }

    //得到信息  设置流式布局
    private void getData1() {

        sku_info = goodsInfoBean1.getData().getItems().getSku_info();

        final List<GoodsInfoBean1.DataBean.ItemsBean.SkuInvBean> sku_inv = goodsInfoBean1.getData().getItems().getSku_inv();
        // 第一个集合的
        List<GoodsInfoBean1.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList1 = sku_info.get(0).getAttrList();

        final String[] names = new String[attrList1.size()];

        for (int j = 0; j < attrList1.size(); j++) {

            names[j] = attrList1.get(j).getAttr_name();
        }


        idFlowlayout.setAdapter(new TagAdapter<String>(names) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {

                TextView tv = new TextView(GoodsSelectActivity.this);

                tv.setTextSize(20);

                tv.setHeight(80);
                tv.setPadding(20, 10, 20, 10);

                Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
                tv.setBackgroundDrawable(drawable);

                tv.setGravity(Gravity.CENTER);

                tv.setTextColor(Color.WHITE);
                tv.setText(s);

                return tv;
            }
        });


        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                position1 = position;
                String price = sku_inv.get(position).getPrice();

                tvMoney.setText("￥" + price);

                if (!TextUtils.isEmpty(goodsInfoBean1.getData().getItems().getSku_info().get(0).getAttrList().get(position).getImg_path())) {

                    //设置 图片
                    Picasso.with(GoodsSelectActivity.this)
                            .load(goodsInfoBean1.getData().getItems().getSku_info().get(0).getAttrList().get(position).getImg_path())
                            .into(ivGoodsImage);

                }


                view.setBackgroundColor(Color.parseColor("#00Bfff"));
                if (tv == view) {

                    idFlowlayout.getChildAt(position).setClickable(false);
                    return true;

                }
                if (tv != null) {

                    tv.setBackgroundColor(Color.parseColor("#808080"));
                }

                tv = view;
                return true;
            }
        });


        setData();
    }

    //设置数据
    private void setData() {

        final String type_name = sku_info.get(0).getType_name();
        tvType.setText(type_name);

        String goods_image = goodsInfoBean1.getData().getItems().getGoods_image();
        Picasso.with(this)
                .load(goods_image)
                .into(ivGoodsImage);

        String goods_name = goodsInfoBean1.getData().getItems().getGoods_name();
        tv_content.setText(goods_name);

        String brand_name = goodsInfoBean1.getData().getItems().getBrand_info().getBrand_name();
        tv_name.setText(brand_name);

        String price = goodsInfoBean1.getData().getItems().getPrice();
        tvMoney.setText("￥" + price);

    }


}
