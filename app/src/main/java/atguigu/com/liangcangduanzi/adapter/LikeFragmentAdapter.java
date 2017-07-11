package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.XiHuanBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/9.
 */

public class LikeFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<XiHuanBean.DataBean.ItemsBean.GoodsBean> items;

    public LikeFragmentAdapter(Context context, List<XiHuanBean.DataBean.ItemsBean.GoodsBean> items) {
        this.context = context;
        this.items = items;

        Log.e("TAG", "ok =="+ items );

    }


    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_type, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String goods_image = items.get(i).getGoods_image();

        Log.e("TAG","imageUrl==" + items.get(i).getGoods_name());
        Picasso.with(context)
                .load(goods_image)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(viewHolder.imageview);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.imageview)
        ImageView imageview;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}