package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.DaRenTuiJianPLBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/14.
 */

public class DaRenTuiJianPLAdapter extends RecyclerView.Adapter<DaRenTuiJianPLAdapter.MyViewHolder> {


    private Context context;
    private List<DaRenTuiJianPLBean.DataBean.ItemsBean> data;
    private LayoutInflater inflater;

    public DaRenTuiJianPLAdapter(Context context, List<DaRenTuiJianPLBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.data = items;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_item_pinglun, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.setData(data.get(position),position);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @InjectView(R.id.ll_video_user_info)
        LinearLayout llVideoUserInfo;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.tv_huifu)
        TextView tvHuifu;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void setData(DaRenTuiJianPLBean.DataBean.ItemsBean itemsBean, int position) {


            Picasso.with(context)
                    .load(itemsBean.getUser_image())
                    .into(ivHeadpic);

            tvName.setText(itemsBean.getUser_name() + ": " + itemsBean.getMsg());
            tvTimeRefresh.setText(itemsBean.getCreate_time());
        }
    }
}
