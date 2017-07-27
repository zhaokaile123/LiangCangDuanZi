package atguigu.com.bilibili.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import atguigu.com.bilibili.R;
import atguigu.com.bilibili.bean.ZhiBoBean;

/**
 * Created by ASUS on 2017/7/26.
 */

public class ListAdapter extends BaseAdapter {

    private Context context;
    private  ZhiBoBean.DataBean.PartitionsBean data ;

    public ListAdapter(Context context, ZhiBoBean.DataBean.PartitionsBean data) {
        this.context = context;
        this.data = data;
        Log.e("TAG",data.toString());
    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = View.inflate(context, R.layout.item_head, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZhiBoBean.DataBean.PartitionsBean partitionsBean = data;

        if(partitionsBean.getLives().get(i).getCover().getSrc()!= null) {

            Glide.with(context)
                    .load(partitionsBean.getLives().get(i).getCover().getSrc())
                    .into(viewHolder.imageView);

        }


        viewHolder.tv_content.setText(partitionsBean.getLives().get(i).getTitle());
        viewHolder.tv_name.setText(partitionsBean.getLives().get(i).getOwner().getName());
        viewHolder.tv_acount.setText(partitionsBean.getLives().get(i).getOnline()+"");


        return convertView;
    }

    static class ViewHolder {

        private ImageView imageView;
        private TextView tv_content;
        private TextView tv_name;
        private TextView tv_acount;

        ViewHolder(View view) {

            imageView = (ImageView) view.findViewById(R.id.iv_icon);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_acount = (TextView) view.findViewById(R.id.tv_acount);

        }
    }
}
