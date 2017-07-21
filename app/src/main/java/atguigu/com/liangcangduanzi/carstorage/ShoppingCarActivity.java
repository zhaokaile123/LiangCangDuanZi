package atguigu.com.liangcangduanzi.carstorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.pay.Pay;
import butterknife.ButterKnife;
import butterknife.InjectView;

import static atguigu.com.liangcangduanzi.R.id.tv_edit;

public class ShoppingCarActivity extends AppCompatActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(tv_edit)
    TextView tvEdit;
    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    @InjectView(R.id.tv_discount)
    TextView tvDiscount;
    @InjectView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @InjectView(R.id.tv_total)
    TextView tvTotal;
    @InjectView(R.id.tv_discountAll)
    TextView tvDiscountAll;
    @InjectView(R.id.tv_toby)
    TextView tvToby;
    @InjectView(R.id.ll_jiesuan)
    LinearLayout llJiesuan;
    @InjectView(R.id.activity_shopping_car)
    LinearLayout activityShoppingCar;
    private List<ShoppingCarBean> data;
    private ShoppingCarAdapter adapter;

    private Pay pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        ButterKnife.inject(this);

        pay = new Pay(this);

        setData();
    }

    private void setData() {

        data = CartStorage.getInstance(this).getAllData();

        adapter = new ShoppingCarAdapter(this, data, cbSelectAll, tvTotal, tvDiscountAll, tvDiscount);

        recycleview.setAdapter(adapter);

        recycleview.setLayoutManager(new LinearLayoutManager(this));

        initListener();

    }

    private boolean isEdit = false;


    private void initListener() {
        //编辑界面的 点击事件
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isEdit = !isEdit;
                if (isEdit) {

                    tvEdit.setText("完成");
                    adapter.setEdit(isEdit);
                    adapter.notifyDataSetChanged();

                } else {

                    tvEdit.setText("编辑");
                    adapter.setEdit(isEdit);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        //结算 吊起支付宝
        tvToby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay.pay(0.01);
            }
        });
    }

}
