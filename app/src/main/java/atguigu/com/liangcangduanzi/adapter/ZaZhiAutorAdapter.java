package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.ZaZhiAutorBean;
import atguigu.com.liangcangduanzi.utils.CircleTransform;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/13.
 */

public class ZaZhiAutorAdapter extends RecyclerView.Adapter<ZaZhiAutorAdapter.MyViewHolder> {


    private Context context;
    private List<ZaZhiAutorBean.DataBean.ItemsBean> data;
    private LayoutInflater inflater;

    public ZaZhiAutorAdapter(Context context, List<ZaZhiAutorBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.data = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_item_autor, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setData(data.get(position),position);

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.imageview)
        ImageView imageview;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void setData(ZaZhiAutorBean.DataBean.ItemsBean bean, int position) {

            tvName.setText(bean.getAuthor_name());
            tvContent.setText(bean.getNote());
            Picasso.with(context)
                    .load(bean.getThumb())
                    .transform(new CircleTransform())
                    .into(imageview);
        }
    }

    /**
     * 监听器
     */
    public interface OnItemClickListener{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }

    private  OnItemClickListener itemClickListener;

    /**
     * 设置item的监听
     * @param l
     */
    public void  setOnItemClickListener(OnItemClickListener l){
        this.itemClickListener = l;
    }
}
