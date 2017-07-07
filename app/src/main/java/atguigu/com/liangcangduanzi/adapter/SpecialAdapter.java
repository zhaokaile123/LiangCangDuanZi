package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.SpecialBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/7.
 */

public class SpecialAdapter extends BaseAdapter {

    private Context context;
    private List<SpecialBean.DataBean.ItemsBean> data = new ArrayList<>();

    public SpecialAdapter(Context context) {
        this.context = context;
    }


    public void refresh(List<SpecialBean.DataBean.ItemsBean> items) {

        Log.e("TAG", "items == " + items);
        //校验
        if (items != null && items.size() >= 0) {
            this.data.clear();
            this.data.addAll(items);
            this.notifyDataSetChanged();
        }

        Log.e("TAG", "data11 == " + data);
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_special, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SpecialBean.DataBean.ItemsBean itemsBean = data.get(position);

        Log.e("TAG","name 0000  == " + data.get(0).getTopic_name());

        String name = itemsBean.getTopic_name();
        viewHolder.tvContent.setText(name);

        String imageUrl = itemsBean.getCover_img();
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(viewHolder.imageview);

        //设置回调接口
        viewHolder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickListener != null){
                    //getLayoutPosition()当前点击View的对应在列表中的位置
                    itemClickListener.onItemClick(position);

                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.imageview)
        ImageView imageview;
        @InjectView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    //定义 接口

    public interface OnItemClickListener{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        void onItemClick(int position);
    }

    private Pager_typeAdapter.OnItemClickListener itemClickListener;

    public void  setOnItemClickListener(Pager_typeAdapter.OnItemClickListener l){
        this.itemClickListener = l;
    }



}
