package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.JiaJu1Bean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/6.
 */

public class Type_itemsAdapter extends BaseAdapter {



    private Context context;
    List<JiaJu1Bean.DataBean.ItemsBean> data;


    public Type_itemsAdapter(Context context, List<JiaJu1Bean.DataBean.ItemsBean> items) {
        this.context = context;
        this.data = items;
    }




    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_jiaju1, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JiaJu1Bean.DataBean.ItemsBean itemsBean = data.get(position);

        viewHolder.tvName.setText(itemsBean.getGoods_name());
        viewHolder.brandName.setText(itemsBean.getBrand_info().getBrand_name());
        viewHolder.likeacount.setText(itemsBean.getLike_count());

        String discount_price = itemsBean.getDiscount_price();
        if (TextUtils.isEmpty(discount_price)) {

            viewHolder.zhekou.setVisibility(View.GONE);
            viewHolder.xian.setVisibility(View.GONE);
            viewHolder.oldPrice.setVisibility(View.GONE);
            viewHolder.price.setText(itemsBean.getPrice());

        } else {
            viewHolder.xian.setVisibility(View.VISIBLE);
            viewHolder.oldPrice.setVisibility(View.VISIBLE);
            viewHolder.zhekou.setVisibility(View.VISIBLE);

            String zhekouUrl = itemsBean.getPromotion_imgurl();

            Picasso.with(context)
                    .load(zhekouUrl)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(viewHolder.zhekou);


            viewHolder.oldPrice.setText(itemsBean.getPrice());
            viewHolder.price.setText(itemsBean.getDiscount_price());
        }


        String imageUrl = itemsBean.getGoods_image();
        //设置图片
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(viewHolder.ivIcon);


        //设置回调接口
        if (itemClickListener != null) {
            //getLayoutPosition()当前点击View的对应在列表中的位置
            itemClickListener.onItemClick(position);
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
        @InjectView(R.id.iv_zhekou)
        ImageView zhekou;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }


//定义 接口

    public interface OnItemClickListener {
        /**
         * 当某条被点击的时候回调
         *
         * @param position
         */
        void onItemClick(int position);

    }

    private Pager_typeAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(Pager_typeAdapter.OnItemClickListener l) {
        this.itemClickListener = l;
    }

}
