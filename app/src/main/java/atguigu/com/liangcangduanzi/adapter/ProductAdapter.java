package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.ProductBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/7.
 */

public class ProductAdapter extends BaseAdapter {

    private Context context;

    private List<ProductBean.DataBean.ItemsBean> data = new ArrayList<>();

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void refresh(List<ProductBean.DataBean.ItemsBean> items) {
        //校验
        if (items != null && items.size() >= 0) {
            this.data.clear();
            this.data.addAll(items);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_product, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUrl = data.get(i).getGoods_image();
        String discount_price = data.get(i).getDiscount_price();
        String like_count = data.get(i).getLike_count();
        String brand_name = data.get(i).getBrand_info().getBrand_name();
        String goods_name = data.get(i).getGoods_name();
        String price = data.get(i).getPrice();

        if (TextUtils.isEmpty(discount_price)) {

            viewHolder.xian.setVisibility(View.GONE);
            viewHolder.oldPrice.setVisibility(View.GONE);
            viewHolder.price.setText(price);
        } else {
            viewHolder.oldPrice.setVisibility(View.VISIBLE);
            viewHolder.xian.setVisibility(View.VISIBLE);
            viewHolder.oldPrice.setText(discount_price);
            viewHolder.price.setText(price);
        }

        viewHolder.tvName.setText(goods_name);
        viewHolder.brandName.setText(brand_name);
        viewHolder.likeacount.setText(like_count);

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(viewHolder.ivIcon);


        //设置回调接口
        if (itemClickListener != null) {
            //getLayoutPosition()当前点击View的对应在列表中的位置
            ProductBean.DataBean.ItemsBean itemsBean = data.get(i);

            itemClickListener.onItemClick(itemsBean);
        }


        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.brand_name)
        TextView brandName;
        @InjectView(R.id.likeacount)
        TextView likeacount;
        @InjectView(R.id.price)
        TextView price;
        @InjectView(R.id.old_price)
        TextView oldPrice;
        @InjectView(R.id.xian)
        TextView xian;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    //定义 接口

    public interface OnItemClickListener {
        /**
         * 当某条被点击的时候回调
         *
         * @param itemsBean
         */
        void onItemClick(ProductBean.DataBean.ItemsBean itemsBean);

    }

    private ProductAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener l) {
        this.itemClickListener = l;
    }
}
