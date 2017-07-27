package atguigu.com.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.bilibili.R;
import atguigu.com.bilibili.bean.ZhiBoBean;
import atguigu.com.bilibili.utils.GlideImageLoader;

/**
 * Created by ASUS on 2017/7/25.
 */

public class ZhiBoAdapter extends RecyclerView.Adapter<ZhiBoAdapter.MyViewHolder> {

    private Context context;
    private List<ZhiBoBean.DataBean.PartitionsBean> data;

    private static int HEAD = 0;
    private static int BODY = 1;

    private View mHeadView;

    private Banner banner;

    private LayoutInflater inflater;
    private ZhiBoBean zhiBoBean;

    public ZhiBoAdapter(Context context, ZhiBoBean zhiBoBean) {

        this.zhiBoBean = zhiBoBean;
        this.context = context;
        this.data = zhiBoBean.getData().getPartitions();
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        } else {
            return BODY;
        }
    }

    public void setHeaderView(View headerView) {
        mHeadView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEAD) {
            return new MyViewHolder(mHeadView);
        } else {
            return new MyViewHolder(inflater.inflate(R.layout.item_fragment_zhibo, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(ZhiBoAdapter.MyViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD) {
            holder.setData(position);
        } else {
            holder.setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size()+1 ;
    }

    private GridView gridView;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;
        private TextView type;
        private TextView zhiboAcount;


        public MyViewHolder(View itemView) {
            super(itemView);

            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icom);
            zhiboAcount = (TextView) itemView.findViewById(R.id.zhiboAcount);
            type = (TextView) itemView.findViewById(R.id.type);



        }

        public void setData(int position) {
            if (ZhiBoAdapter.this.getItemViewType(position) == HEAD) {
                banner = (Banner) mHeadView.findViewById(R.id.banner);
                initBanner();

            } else {

                ListAdapter adater = new ListAdapter(context,data.get(position-1));
                gridView = (GridView) itemView.findViewById(R.id.gridview);
                gridView.setAdapter(adater);

                ZhiBoBean.DataBean.PartitionsBean partitionsBean = data.get(position-1);

                Glide.with(context)
                        .load(partitionsBean.getPartition().getSub_icon().getSrc())
                        .into(iv_icon);

                type.setText(partitionsBean.getPartition().getName());
                zhiboAcount.setText(partitionsBean.getPartition().getCount()+"");

            }
        }

        private List<String> image;

        private void initBanner() {
            List<ZhiBoBean.DataBean.BannerBean> banners = zhiBoBean.getData().getBanner();

            if (banners != null && banners.size() > 0) {
                image = new ArrayList();
                for (int i = 0; i < banners.size(); i++) {
                    image.add(banners.get(i).getImg());
                }
            }
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());     //设置图片集合
            banner.setImages(image);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }
}
