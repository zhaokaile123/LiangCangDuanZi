package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.BrandBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/6.
 */

public class BrandAdapter extends BaseAdapter {

    private Context context;
    private List<BrandBean.DataBean.ItemsBean> data = new ArrayList<>();

    public BrandAdapter(Context context) {
        this.context = context;

    }

    public void refresh(List<BrandBean.DataBean.ItemsBean> list) {
        //校验
        if (list != null && list.size() >= 0) {
            this.data.clear();
            this.data.addAll(list);
            this.notifyDataSetChanged();
        }
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
            convertView = View.inflate(context, R.layout.item_brand, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BrandBean.DataBean.ItemsBean itemsBean = data.get(position);
        viewHolder.tvName.setText(itemsBean.getBrand_name());

        String imageUrl = itemsBean.getBrand_logo();

        Picasso.with(context)
                .load(imageUrl)
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
        @InjectView(R.id.go)
        ImageView go;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
