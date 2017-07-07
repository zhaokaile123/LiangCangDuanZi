package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.HomeBean;

/**
 * Created by ASUS on 2017/7/6.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private Context context;
    private int currentType;

    private static final int ONE = 0;
    private static final int TWO = 1;
    private static final int THREE = 2;

    private LayoutInflater inflater;
    private List<HomeBean.DataBean.ItemsBean.ListBean> data;
    public HomeAdapter(Context context, List<HomeBean.DataBean.ItemsBean.ListBean> list) {
        this.context = context;
        this.data = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).getHome_type().equals("1")) {
             currentType = ONE;
        }
        if(data.get(position).getHome_type().equals("2")) {
            currentType = TWO;
        }
        if(data.get(position).getHome_type().equals("4")) {
            currentType = THREE;
        }
        return currentType;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType == ONE) {


         return new OneViewHolder(context,inflater.inflate(R.layout.pager_home_one, parent, false));

       }else if(viewType == TWO) {

           return new TwoViewHolder(context, inflater.inflate(R.layout.pager_home_two,parent,false));

       }else if(viewType == THREE) {

           return new FourViewHolder(context, inflater.inflate(R.layout.pager_home_four,parent, false));

       }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

       if(getItemViewType(position) == ONE) {
           OneViewHolder oneViewHolder = (OneViewHolder) holder;
           //设置数据Banner的数据
           oneViewHolder.setData(data.get(position).getOne().getPic_url());
       }else if(getItemViewType(position) == TWO) {

           TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
           //设置数据Banner的数据
           twoViewHolder.setData(data.get(position));

       }else if(getItemViewType(position) == THREE) {

           FourViewHolder fourViewHolder = (FourViewHolder) holder;
           fourViewHolder.setData(data.get(position));
       }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(itemClickListener != null){
                    //getLayoutPosition()当前点击View的对应在列表中的位置

                    itemClickListener.onItemClick(position);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //单张图片的  viewholoder
    class OneViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        private ImageView iv_one;

        public OneViewHolder(Context context,View itemView) {
            super(itemView);
            this.context = context;
            iv_one = (ImageView) itemView.findViewById(R.id.iv_one);
        }

        public void setData(String pic_url) {

            Picasso.with(context)
                    .load(pic_url)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_one);

        }
    }

    // 左右两张图片的  viewholoder
    class TwoViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private ImageView iv_left;
        private ImageView iv_right;
        public TwoViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;

            iv_left = (ImageView) itemView.findViewById(R.id.iv_left);
            iv_right = (ImageView) itemView.findViewById(R.id.iv_right);
        }

        public void setData(HomeBean.DataBean.ItemsBean.ListBean listBean) {
            String imageUrlLeft = listBean.getOne().getPic_url();
            Picasso.with(context)
                    .load(imageUrlLeft)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_left);


            String imagerUrlRigtht = listBean.getTwo().getPic_url();
            Picasso.with(context)
                    .load(imagerUrlRigtht)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_right);

        }
    }

    // 四张图片的  viewholoder
     class FourViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_left_top;
        private ImageView iv_left_bottom;
        private ImageView iv_right_top;
        private ImageView iv_right_bottom;


        private Context context;
        public FourViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;

            iv_left_top = (ImageView) itemView.findViewById(R.id.iv_left_top);
            iv_left_bottom = (ImageView) itemView.findViewById(R.id.iv_left_bottom);
            iv_right_top = (ImageView) itemView.findViewById(R.id.iv_right_top );
            iv_right_bottom = (ImageView) itemView.findViewById(R.id.iv_right_bottom);
        }

        public void setData(HomeBean.DataBean.ItemsBean.ListBean listBean) {

            String lt = listBean.getOne().getPic_url();//左上
            String lb = listBean.getTwo().getPic_url(); //zuo xia
            String rt = listBean.getThree().getPic_url(); //you shang
            String rb = listBean.getFour().getPic_url();

            Picasso.with(context)
                    .load(lt)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_left_top);


            Picasso.with(context)
                    .load(lb)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_left_bottom);

            Picasso.with(context)
                    .load(rt)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_right_top);

            Picasso.with(context)
                    .load(rb)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_right_bottom);

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
