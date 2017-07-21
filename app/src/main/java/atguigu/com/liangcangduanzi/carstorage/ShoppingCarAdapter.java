package atguigu.com.liangcangduanzi.carstorage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.view.AddSubView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/19.
 */

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.MyViewHolder> {


    private Context context;
    private List<ShoppingCarBean> data;
    private CheckBox cbSelectAll;
    private TextView tvTotal;
    private LayoutInflater inflater;
    private TextView tvDiscountAll;
    private TextView tvDsicount;
    private ShoppingCarBean goodsBean;

    public ShoppingCarAdapter(Context context, List<ShoppingCarBean> data, CheckBox checkBox, TextView tvTotal, TextView tvDiscountAll, TextView tvDsicount) {
        this.context = context;
        this.data = data;
        this.cbSelectAll = checkBox;
        this.tvTotal = tvTotal;
        this.tvDiscountAll = tvDiscountAll;
        this.tvDsicount = tvDsicount;


        inflater = LayoutInflater.from(context);

        //显示总价格
        showTotalPrice();

        //校验全选
        checkAll();

        setListener();//點擊全選

    }

    private boolean isEdit;

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }


    private void setListener() {

        cbSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAll_none(cbSelectAll.isChecked());
                checkAll();
                showTotalPrice();
            }
        });


    }

    //显示总价格  和節省價格
    private void showTotalPrice() {

        tvTotal.setText("总价 ： ￥" + getTotalPrice());
        tvDiscountAll.setText("以节省 ：￥" + getToalDiscountPrice());
        tvDsicount.setText("-￥" + getToalDiscountPrice());
    }

    //得到節省的价格
    private double getToalDiscountPrice() {

        double total1 = 0;
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                ShoppingCarBean goodsBean = data.get(i);
                if (goodsBean.isChecked()) {

                    total1 += Double.parseDouble(goodsBean.getDiscount()) * Double.parseDouble(goodsBean.getNumb() + "");
                }

            }

        }

        return total1;
    }

    //得到总价格
    private double getTotalPrice() {

        double total = 0;
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                ShoppingCarBean goodsBean = data.get(i);
                if (goodsBean.isChecked())

                    total += Double.parseDouble(goodsBean.getPrice().substring(1)) * Double.parseDouble(goodsBean.getNumb() + "");

            }
        }
        return total;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(inflater.inflate(R.layout.item_shop_cart, null));

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.根据位置得到数据
        goodsBean = data.get(position);
        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片

        Picasso.with(context)
                .load(goodsBean.getImageUrl())
                .into(holder.ivGov);

        holder.tvBarandName.setText(goodsBean.getName());

        //设置价格
        holder.tvPrice.setText(goodsBean.getPrice());

        holder.tvNumber.setText(goodsBean.getNumb() + "");


        //完成界面的设置

        holder.addSubView.setValue(goodsBean.getNumb());
        holder.tvBarandName1.setText(goodsBean.getName());
        holder.tvPrice1.setText(goodsBean.getPrice());

        if(!isEdit) {
            holder.llEdit.setVisibility(View.GONE);
            holder.llComplete.setVisibility(View.VISIBLE);
        }else {
            holder.llEdit.setVisibility(View.VISIBLE);
            holder.llComplete.setVisibility(View.GONE);
        }


    }

    //校验 全选 按钮  是否需要被选中    根据上面按钮的状态 来显示下面全选按钮的状态
    public void checkAll() {

        if (data != null && data.size() > 0) {
            int number = 0;
            for (int i = 0; i < data.size(); i++) {
                ShoppingCarBean goodsBean = data.get(i);
                if (!goodsBean.isChecked()) {  //如果有任何一个bean没有被勾选  那么全选按钮就设置为 非全选 也就是fasle
                    cbSelectAll.setChecked(false);
                } else {  // 如果有bean 被勾选了  那么把勾选的数量累加
                    number++;
                }
            }
            //如果勾选的数量等于 数据的总量  那么表示都勾选了  设置为全选
            if (data.size() == number) {
                cbSelectAll.setChecked(true);
            }
        } else {  //没有数据
            cbSelectAll.setChecked(false);
        }
    }


    //校验 全删按钮 是否需要被选中
    //传递过来是什么状态 就是什么状态    根据人为点击全选  来设置上面按钮的状态
    public void checkAll_none(boolean checked) {

        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setChecked(checked);

                cbSelectAll.setChecked(checked);

                notifyItemChanged(i);
            }
        } else {  //没有 数据 设置为非全选
            cbSelectAll.setChecked(false);
        }
    }

    /**
     * 删除商品
     */
    public void deleteData() {

        if (data != null && data.size() > 0) {

            for (int i = 0; i < data.size(); i++) {

                ShoppingCarBean goodsBean = data.get(i);
                if (goodsBean.isChecked()) {
                    data.remove(goodsBean);

                    CartStorage.getInstance(context).deleteData(goodsBean);

                    notifyDataSetChanged();
                    i--;
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_barand_name)
        TextView tvBarandName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;
        @InjectView(R.id.tv_number)
        TextView tvNumber;
        @InjectView(R.id.ll_edit)
        LinearLayout llEdit;
        @InjectView(R.id.add_sub_view)
        AddSubView addSubView;
        @InjectView(R.id.tv_barand_name1)
        TextView tvBarandName1;
        @InjectView(R.id.tv_price1)
        TextView tvPrice1;
        @InjectView(R.id.tv_delete)
        TextView tvDelete;
        @InjectView(R.id.ll_complete)
        LinearLayout llComplete;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            //这个item的点击事件  可以用接口传递     在这里使用的是直接做
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点一下就设置成 反的
                    //状态取反         因为布局文件已经设置checkBox 为不可点击   所有设置item的点击事件  来让goodsBean的状态来取反
                    ShoppingCarBean goodsBean = data.get(getLayoutPosition());
                    goodsBean.setChecked(!goodsBean.isChecked());

                    //刷新适配器
                    notifyItemChanged(getLayoutPosition());

                    //重新显示总价格
                    showTotalPrice();

                    checkAll(); //校验 是否需要 全选
                }
            });

           tvDelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Toast.makeText(context, "sahnchu", Toast.LENGTH_SHORT).show();
               }
           });

           addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
               @Override
               public void numberChange(int value) {
                   goodsBean.setNumb(value);
                   CartStorage.getInstance(context).updateData(goodsBean);
                   showTotalPrice();

               }
           });
        }
    }
}