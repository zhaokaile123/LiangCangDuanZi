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

import java.util.ArrayList;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.ZaZhiBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/12.
 */

public class ZaZhiItemAdapter extends RecyclerView.Adapter<ZaZhiItemAdapter.MyViewHolder> {


    private Context context;

    private LayoutInflater inflater;
    private ArrayList<ZaZhiBean> beanitems;

    public ZaZhiItemAdapter(Context context, ArrayList<ZaZhiBean> beanitems) {

        this.beanitems = beanitems;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_item_zazhi1, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setData(beanitems.get(position), position);

    }

    @Override
    public int getItemCount() {
        return beanitems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_image)
        ImageView ivImage;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

        }

        public void setData(ZaZhiBean zaZhiBean, final int position) {

            String cover_img_new = zaZhiBean.getCover_img_new();

            Picasso.with(context)
                    .load(cover_img_new)
                    .into(ivImage);

            tvType.setText(zaZhiBean.getCat_name());
            tvContent.setText(zaZhiBean.getTopic_name());

            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(position);
                }
            });
        }
    }


    public interface OnItemClickListener {
        /**
         * 当某条被点击的时候回调
         *
         * @param position
         */
        void onItemClick(int position);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }
}
