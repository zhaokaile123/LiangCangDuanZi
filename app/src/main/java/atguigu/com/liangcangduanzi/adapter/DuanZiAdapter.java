package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wx.goodview.GoodView;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.Special_WebViewActivity;
import atguigu.com.liangcangduanzi.bean.DaunZiBean;
import atguigu.com.liangcangduanzi.utils.CircleTransform;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by ASUS on 2017/7/12.
 */

public class DuanZiAdapter extends BaseAdapter {

    List<DaunZiBean.ListBean> data;

    private Context context;
    private GoodView goodView;


    public DuanZiAdapter(Context context, List<DaunZiBean.ListBean> list) {
        this.data = list;
        this.context = context;

        goodView = new GoodView(context);

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


    private int up;
    private int down;
    private int share;
    private int pingtlun;


    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        final ViewHolder viewHolder;
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
        up = Integer.parseInt(data.get(i).getUp());
        viewHolder.tvShenheDingNumber.setText(up + "");

        down = data.get(i).getDown();
        viewHolder.tvShenheCaiNumber.setText(down + "");

        share = data.get(i).getForward();
        viewHolder.tvPostsNumber.setText(share + "");


        if (data.get(i).getTop_comments() != null) {

            String name1 = data.get(i).getTop_comments().get(0).getU().getName().toString();//评论者 1
            String content1 = data.get(i).getTop_comments().get(0).getContent().toString();

            SpannableStringBuilder builder = new SpannableStringBuilder(name1 + ": " + content1);
            ForegroundColorSpan redSpan = new ForegroundColorSpan(0xff4169E1);
            builder.setSpan(redSpan, 0, name1.length(), 0xff000000);
            viewHolder.tvName111.setText(builder);

            if (data.get(i).getTop_comments().size() > 1) {

                String name2 = data.get(i).getTop_comments().get(1).getU().getName().toString();//2
                String content2 = data.get(i).getTop_comments().get(1).getContent().toString();

                SpannableStringBuilder builder1 = new SpannableStringBuilder(name2 + ": " + content2);
                ForegroundColorSpan redSpan1 = new ForegroundColorSpan(0xff4169E1);
                builder1.setSpan(redSpan1, 0, name2.length(), 0xff000000);
                viewHolder.tvName3.setText(builder1);


            } else {
                viewHolder.tvName3.setVisibility(View.GONE);
            }

        } else {
            viewHolder.llPinglun.setVisibility(View.GONE);
        }


        //顶的点击事件
        viewHolder.llDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewHolder.llDing.setClickable(false);
                viewHolder.llCai.setClickable(false);

                viewHolder.tvDing.setBackgroundResource(R.drawable.shenhe_ding_pic_an);
                viewHolder.tvShenheDingNumber.setText(up + 1 + "");

                goodView.setTextInfo("+1", Color.RED, 30);
                goodView.show(view);
            }
        });

        //踩的点击事件
        viewHolder.llCai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewHolder.llDing.setClickable(false);
                viewHolder.llCai.setClickable(false);
                viewHolder.tvShenheCaiNumber.setText(down + 1 + "");

                viewHolder.tvCai.setBackgroundResource(R.drawable.shenhe_cai_pic_an);

                goodView.setTextInfo("+1", Color.RED, 30);
                goodView.show(view);
            }
        });

        viewHolder.tvPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = data.get(i).getShare_url();
                Intent intent = new Intent(context, Special_WebViewActivity.class);
                intent.putExtra("url", url);
                context.startActivity(intent);

            }
        });


        viewHolder.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showShare();

            }
        });

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
        @InjectView(R.id.tv_name111)
        TextView tvName111;
        @InjectView(R.id.tv_name1)
        TextView tvName1;
        @InjectView(R.id.tv_content1)
        TextView tvContent1;
        @InjectView(R.id.tv_name3)
        TextView tvName3;
        @InjectView(R.id.tv_name2)
        TextView tvName2;
        @InjectView(R.id.tv_content2)
        TextView tvContent2;

        @InjectView(R.id.ll_pinglun)
        LinearLayout llPinglun;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);


        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(context);

    }

}
