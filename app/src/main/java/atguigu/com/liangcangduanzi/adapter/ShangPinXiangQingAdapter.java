package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.GoodsInfoBean1;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/10.
 */

public class ShangPinXiangQingAdapter extends RecyclerView.Adapter {


    @InjectView(R.id.tv_bt)
    TextView tvBt;
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    private int currentType;

    private static final int NONE = 0;//  zhiyou  text  bianti
    private static final int ONE1 = 1;  //  pictrue
    private static final int TWO1 = 2; // text  neirong


    List<GoodsInfoBean1.DataBean.ItemsBean.GoodsInfoBean> data;
    private Context context;
    private LayoutInflater inflater;

    private GoodsInfoBean1.DataBean.ItemsBean goodsInfoBean1;

    public ShangPinXiangQingAdapter(Context context, GoodsInfoBean1.DataBean.ItemsBean goodsInfoBean1) {
        this.context = context;
        this.data = goodsInfoBean1.getGoods_info();
        inflater = LayoutInflater.from(context);
        this.goodsInfoBean1 = goodsInfoBean1;
    }


    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType() == 0) {
            currentType = NONE;
        }
        if (data.get(position).getType() == 1) {
            currentType = ONE1;
        }
        if (data.get(position).getType() == 2) {
            currentType = TWO1;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == NONE) {

            return new NONEViewHolder(context, inflater.inflate(R.layout.fragment_xq_bt, parent, false));

        } else if (viewType == ONE1) {

            return new P_AND_TViewHolder(context, inflater.inflate(R.layout.fragment_xq_tp, parent, false));

        } else if (viewType == TWO1) {

            return new PICTRUEViewHolder(context, inflater.inflate(R.layout.fragment_xq_content, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == NONE) {
            NONEViewHolder oneViewHolder = (NONEViewHolder) holder;
            //设置数据Banner的数据

            oneViewHolder.setData(data.get(position).getContent().getText());

        } else if (getItemViewType(position) == ONE1) {

            P_AND_TViewHolder twoViewHolder = (P_AND_TViewHolder) holder;
            //设置数据Banner的数据
            twoViewHolder.setData(data.get(position).getContent().getImg());

        } else if (getItemViewType(position) == TWO1) {

            PICTRUEViewHolder fourViewHolder = (PICTRUEViewHolder) holder;
            fourViewHolder.setData(data.get(position).getContent().getText(),goodsInfoBean1.getGoods_desc());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //标题的
    private class NONEViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView tv;

        public NONEViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            tv = (TextView) view.findViewById(R.id.tv_bt);
        }
        public void setData(String content) {

            if(!TextUtils.isEmpty(content)) {

                tv.setText(content);
            }else {
                tv.setText(goodsInfoBean1.getGoods_desc());
            }

        }
    }

    private class P_AND_TViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private ImageView imageView;

        public P_AND_TViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            imageView = (ImageView) view.findViewById(R.id.iv);
        }

        public void setData(String img) {
            Picasso.with(context)
                    .load(img)
                    .placeholder(R.drawable.comment_no_data)
                    .error(R.drawable.comment_no_data)
                    .into(imageView);

        }
    }

    private class PICTRUEViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView tv_content;

        public PICTRUEViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            tv_content = (TextView) view.findViewById(R.id.tv_content);
        }

        public void setData(String s, String text) {
            if(!TextUtils.isEmpty(text)) {
                tv_content.setText(text);
            }else {
                tv_content.setText(s);
            }


        }
    }


}
