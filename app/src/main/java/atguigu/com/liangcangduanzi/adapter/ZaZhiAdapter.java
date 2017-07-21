package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.ZaZhiBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/12.
 */

public class ZaZhiAdapter extends RecyclerView.Adapter<ZaZhiAdapter.MyViewHolder> {


    private final String date;
    private Context context;

    private LayoutInflater inflater;
    private ArrayList<ZaZhiBean> beanitems;

    public ZaZhiAdapter(Context context,ArrayList<ZaZhiBean> beanitems) {

        this.beanitems = beanitems;
        this.context = context;
        inflater = LayoutInflater.from(context);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = sDateFormat.format(new Date());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_item_zazhi, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setData(beanitems.get(position),position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanitems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        private LinearLayout ll_time;
        private ImageView imageView;

        private String nextTime;
        private String currentTime;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
            ll_time = (LinearLayout) view.findViewById(R.id.ll_time);
            imageView = (ImageView) view.findViewById(R.id.iv_image);
        }

        public void setData(ZaZhiBean zaZhiBean,int position) {
            String time = zaZhiBean.getAddtime();
            if(position != beanitems.size()-1) {  //防止数组下标越界
                nextTime = beanitems.get(position + 1).getAddtime().substring(0,time.indexOf(" "));  //得到下一次的时间
            }

            currentTime = time.substring(0,time.indexOf(" "));//当前时间

            Log.e("TAG","currentTime==" + currentTime);

           String fristime = beanitems.get(0).getAddtime().substring(0,time.indexOf(" "));// 第一个 显示的 时间

            Log.e("TAG",fristime);




            String cover_img_new = zaZhiBean.getCover_img_new();

            Picasso.with(context)
                    .load(cover_img_new)
                    .into(imageView);

            tvType.setText(zaZhiBean.getCat_name());
            tvContent.setText(zaZhiBean.getTopic_name());

            if(currentTime.equals(nextTime)) {  //如果都是当天 发送的内容
                ll_time.setVisibility(View.GONE);
            }else {
                //  如果第一次的时间 == 当前的时间
                if(fristime.equals(currentTime)) {
                    ll_time.setVisibility(View.GONE);
                }else {
                    ll_time.setVisibility(View.VISIBLE);
                }

                tvTime.setText(currentTime);
            }

        }
    }


    public interface OnItemClickListener{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }

    private  OnItemClickListener itemClickListener;

    public void  setOnItemClickListener(OnItemClickListener l){
        this.itemClickListener = l;
    }
}
