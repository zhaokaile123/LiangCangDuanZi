package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.GuanZhuFenSiBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/9.
 */

public class GuanZhuFenSiAdapter extends BaseAdapter {

    private Context context;
    List<GuanZhuFenSiBean.DataBean.ItemsBean.UsersBean> users;

    public GuanZhuFenSiAdapter(Context context, List<GuanZhuFenSiBean.DataBean.ItemsBean.UsersBean> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users == null ? 0 : users.size();
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
            convertView = View.inflate(context, R.layout.item_guanzhu, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String user_name = users.get(i).getUser_name();
        String orig = users.get(i).getUser_image().getOrig();
        String user_desc = users.get(i).getUser_desc();

        viewHolder.tvDuty.setText(user_desc);
        viewHolder.tvName.setText(user_name);


        Picasso.with(context)
                .load(orig)
                .placeholder(R.drawable.comment_no_data)
                .error(R.drawable.comment_no_data)
                .into(viewHolder.imageview);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.imageview)
        ImageView imageview;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_duty)
        TextView tvDuty;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
