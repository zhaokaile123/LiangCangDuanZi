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
import atguigu.com.liangcangduanzi.bean.GiftAllBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/7.
 */

public class Gift_itemsAdapter extends BaseAdapter {


    private Context context;
    private List<GiftAllBean.DataBean.ItemsBean> data;

    public Gift_itemsAdapter(Context context, List<GiftAllBean.DataBean.ItemsBean> items) {
        this.context =context;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context,R.layout.item_gift,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String url = data.get(i).getGoods_image();

        viewHolder.tvName.setText(data.get(i).getGoods_name());
        viewHolder.brandName.setText(data.get(i).getBrand_info().getBrand_name());
        viewHolder.likeacount.setText(data.get(i).getLike_count());
        String discount_price = data.get(i).getDiscount_price();

        if (TextUtils.isEmpty(discount_price)) {
            viewHolder.xian.setVisibility(View.GONE);
            viewHolder.oldPrice.setVisibility(View.GONE);
            viewHolder.price.setText(data.get(i).getPrice());
        } else {
            viewHolder.oldPrice.setText(data.get(i).getPrice());
            viewHolder.price.setText(data.get(i).getDiscount_price());
        }

        //设置图片
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(viewHolder.ivIcon);


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


}
