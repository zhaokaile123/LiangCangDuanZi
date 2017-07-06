package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.DarenBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/7.
 */

public class DarenAdapter extends BaseAdapter {

    private Context context;
    private List<DarenBean.DataBean.ItemsBean> data = new ArrayList<>();

    public DarenAdapter(Context context) {
        this.context = context;

    }

    public void refresh(List<DarenBean.DataBean.ItemsBean> items) {
        //校验
        if (data != null && data.size() >= 0) {
            this.data.clear();
            this.data.addAll(items);
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.items_fragment_daren, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data.get(i).get




        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.imageview)
        ImageView imageview;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.zhiwei)
        TextView zhiwei;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
