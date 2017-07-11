package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Special_WebViewActivity;
import atguigu.com.liangcangduanzi.bean.HomeBean1;

/**
 * Created by ASUS on 2017/7/6.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private Context context;
    private int currentType;

    private static final int ONE = 0;
    private static final int TWO = 1;
    private static final int THREE = 2;
    private static final int FOUR = 3;

    private LayoutInflater inflater;
    private List<HomeBean1.DataBean.ItemsBean.ListBeanX> data;


    public HomeAdapter(Context context, List<HomeBean1.DataBean.ItemsBean.ListBeanX> list) {

        this.context = context;
        this.data = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {

        if(data.get(position).getHome_type() == 1) {
             currentType = ONE;
        }
        if(data.get(position).getHome_type() == 2) {
            currentType = TWO;
        }
        if(data.get(position).getHome_type() == 4) {
            currentType = THREE;
        }if(data.get(position).getHome_type() == 6) {
            currentType = FOUR;
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

       }else if(viewType == FOUR) {
           return new SixViewHolder(context,inflater.inflate(R.layout.pager_home_one, parent, false));
       }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


       if(getItemViewType(position) == ONE) {
           OneViewHolder oneViewHolder = (OneViewHolder) holder;
           //设置数据Banner的数据
           oneViewHolder.setData(data.get(position).getOne().getPic_url(),data.get(position));
       }else if(getItemViewType(position) == TWO) {

           TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
           //设置数据Banner的数据
           twoViewHolder.setData(data.get(position));

       }else if(getItemViewType(position) == THREE) {

           FourViewHolder fourViewHolder = (FourViewHolder) holder;
           fourViewHolder.setData(data.get(position));

       }else if(getItemViewType(position) == FOUR) {

           SixViewHolder sixViewHolder = (SixViewHolder) holder;
           sixViewHolder.setData(data.get(position).getList().get(0).getPic_url(),data.get(position));

       }

    }

    @Override
    public int getItemCount() {
        return data == null ? 0:data.size();
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

        public void setData(String pic_url, final HomeBean1.DataBean.ItemsBean.ListBeanX listBean) {

            Picasso.with(context)
                    .load(pic_url)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String url = listBean.getOne().getTopic_url();
                    String content = listBean.getOne().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);

                }
            });

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

        public void setData (final HomeBean1.DataBean.ItemsBean.ListBeanX listBean) {
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

            iv_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = listBean.getOne().getTopic_url();
                    String content = listBean.getOne().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);
                }
            });

            iv_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = listBean.getTwo().getTopic_url();
                    String content = listBean.getTwo().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);
                }
            });


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

        public void setData(final HomeBean1.DataBean.ItemsBean.ListBeanX listBean) {

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

            iv_left_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = listBean.getOne().getTopic_url();
                    String content = listBean.getOne().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);
                }
            });

            iv_left_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = listBean.getTwo().getTopic_url();
                    String content = listBean.getTwo().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);
                }
            });

            iv_right_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = listBean.getThree().getTopic_url();
                    String content = listBean.getThree().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);

                }
            });

            iv_right_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = listBean.getFour().getTopic_url();
                    String content = listBean.getFour().getTopic_name();
                    Intent intent = new Intent(context, Special_WebViewActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("content",content);
                    context.startActivity(intent);
                }
            });
        }
    }
    //单张购物图片
     class SixViewHolder extends RecyclerView.ViewHolder{

         private Context context;
         private ImageView iv_one;

         public SixViewHolder(Context context,View itemView) {
             super(itemView);
             this.context = context;
             iv_one = (ImageView) itemView.findViewById(R.id.iv_one);
         }


        public void setData(String pic_url, final HomeBean1.DataBean.ItemsBean.ListBeanX listBeanX) {
            Picasso.with(context)
                    .load(pic_url)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(iv_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
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
