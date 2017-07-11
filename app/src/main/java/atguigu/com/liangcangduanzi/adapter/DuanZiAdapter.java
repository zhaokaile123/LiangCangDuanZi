package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.bean.DaunZiBean;
import atguigu.com.liangcangduanzi.utils.CircleTransform;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ASUS on 2017/7/11.
 */

public class DuanZiAdapter extends BaseAdapter {

    List<DaunZiBean.ListBean> data;
    private Context context;
    private TextView tvContext;
    private TextView tvPlayNums;
    private TextView tvVideoDuration;
    private ImageView ivCommant;
    private TextView tvCommantContext;

    public DuanZiAdapter(Context context, List<DaunZiBean.ListBean> list) {
        this.context = context;
        this.data = list;

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
            convertView = View.inflate(context, R.layout.item_duanzi, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = data.get(i).getText();

        viewHolder.tvContext.setText(name);  //段子 内容

        viewHolder.tvName.setText(data.get(i).getU().getName());// 作者
        viewHolder.tvTimeRefresh.setText(data.get(i).getPasstime());// 时间

        Picasso.   //头像
            with(context)
            .load(data.get(i).getU().getHeader().get(0))
            .transform(new CircleTransform())
            .into(viewHolder.ivHeadpic);

        //设置点赞，踩,转发
        viewHolder.tvShenheDingNumber.setText(data.get(i).getUp());
        viewHolder.tvShenheCaiNumber.setText(data.get(i).getDown() + "");
        viewHolder.tvPostsNumber.setText(data.get(i).getForward() + "");


        String name1 = data.get(i).getTop_comments().get(0).getU().getName().toString();//评论者 1
        String content1 = data.get(i).getTop_comments().get(0).getContent().toString();

        SpannableStringBuilder builder = new SpannableStringBuilder(name1 + ": " + content1);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(0xff4169E1);
        builder.setSpan(redSpan, 0, name1.length(), 0xff000000);
        viewHolder.tv_name111.setText(builder );

        if(data.get(i).getTop_comments().size() > 1) {

            String name2 = data.get(i).getTop_comments().get(1).getU().getName().toString();//2
            String content2 = data.get(i).getTop_comments().get(1).getContent().toString();

            SpannableStringBuilder builder1 = new SpannableStringBuilder(name2 + ": " + content2);
            ForegroundColorSpan redSpan1 = new ForegroundColorSpan(0xff4169E1);
            builder1.setSpan(redSpan1, 0, name2.length(), 0xff000000);
            viewHolder.tvname3.setText(builder1 );

        }else {
            viewHolder.tvname3.setVisibility(View.GONE);
        }


        int id = Integer.parseInt(data.get(i).getId());//用这个id 和 详情的拼接下


        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @InjectView(R.id.ll_video_user_info)
        LinearLayout llVideoUserInfo;
        @InjectView(R.id.tv_context)
        TextView tvContext;
        @InjectView(R.id.iv_video_kind)
        ImageView ivVideoKind;
        @InjectView(R.id.tv_video_kind_text)
        TextView tvVideoKindText;
        @InjectView(R.id.tv_ding)
        TextView tvDing;
        @InjectView(R.id.tv_shenhe_ding_number)
        TextView tvShenheDingNumber;
        @InjectView(R.id.ll_ding)
        LinearLayout llDing;
        @InjectView(R.id.tv_cai)
        TextView tvCai;
        @InjectView(R.id.tv_shenhe_cai_number)
        TextView tvShenheCaiNumber;
        @InjectView(R.id.ll_cai)
        LinearLayout llCai;
        @InjectView(R.id.tv_share)
        TextView tvShare;
        @InjectView(R.id.tv_posts_number)
        TextView tvPostsNumber;
        @InjectView(R.id.ll_share)
        LinearLayout llShare;
        @InjectView(R.id.tv_pinglun)
        TextView tvPinglun;
        @InjectView(R.id.tv_download_number)
        TextView tvDownloadNumber;
        @InjectView(R.id.ll_download)
        LinearLayout llDownload;
        @InjectView(R.id.tv_name1)
        TextView tvName1;
        @InjectView(R.id.tv_content1)
        TextView tvContent1;
        @InjectView(R.id.tv_name2)
        TextView tvName2;
        @InjectView(R.id.tv_content2)
        TextView tvContent2;
        @InjectView(R.id.tv_name3)
        TextView tvname3;
        @InjectView(R.id.tv_name111)
        TextView tv_name111;



        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
