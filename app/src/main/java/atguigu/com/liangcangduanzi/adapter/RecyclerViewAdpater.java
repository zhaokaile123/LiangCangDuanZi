package atguigu.com.liangcangduanzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.GifAndImageActivity;
import atguigu.com.liangcangduanzi.bean.BSTuiJianBean;
import atguigu.com.liangcangduanzi.utils.CircleTransform;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by ASUS on 2017/7/11.
 */

public class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerViewAdpater.BaseViewHolder> {

    private final Context mContext;
    private final List<BSTuiJianBean.ListBean>  datas;

    /**
     * 视频
     */
    private static final int TYPE_VIDEO = 0;

    /**
     * 图片
     */
    private static final int TYPE_IMAGE = 1;

    /**
     * 文字
     */
    private static final int TYPE_TEXT = 2;

    /**
     * GIF图片
     */
    private static final int TYPE_GIF = 3;

    /**
     * 软件推广
     */
    private static final int TYPE_AD = 4;


    public RecyclerViewAdpater(Context mContext, List<BSTuiJianBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
    }

    /**
     * 初始化布局和创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerViewAdpater.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return initViewHolder(viewType);
    }

    private RecyclerViewAdpater.BaseViewHolder initViewHolder(int itemViewType) {
        RecyclerViewAdpater.BaseViewHolder viewHolder = null;
        View convertView = null;
        switch (itemViewType) {
            case TYPE_VIDEO://视频
                convertView = View.inflate(mContext, R.layout.all_video_item, null);
                viewHolder = new VideoHoder(convertView);

                break;
            case TYPE_IMAGE://图片
                convertView = View.inflate(mContext, R.layout.all_image_item, null);
                viewHolder = new ImageHolder(convertView);
                break;
            case TYPE_TEXT://文字
                convertView = View.inflate(mContext, R.layout.all_text_item, null);
                viewHolder = new TextHolder(convertView);
                break;
            case TYPE_GIF://gif
                convertView = View.inflate(mContext, R.layout.all_gif_item, null);
                viewHolder = new GifHolder(convertView);

                break;
            case TYPE_AD://软件广告
                convertView = View.inflate(mContext, R.layout.all_ad_item, null);
//                viewHolder = new ADHolder(convertView);
                break;
        }
        return viewHolder;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewAdpater.BaseViewHolder holder, int position) {
        holder.setData(datas.get(position));

    }

    /**
     * 返回的是总数据
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }


    /**
     * 根据对应的位置得到对应的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象
        BSTuiJianBean.ListBean listBean = datas.get(position);
        String type = listBean.getType();//得到类型
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else {
            itemViewType = TYPE_AD;//广播
        }
        return itemViewType;
    }


    class ADHolder extends BaseViewHolder{
        TextView tvContext;
        ImageView ivImageIcon;
        Button btnInstall;

        ADHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            btnInstall = (Button) convertView.findViewById(R.id.btn_install);
            ivImageIcon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
        }

        public void setData(BSTuiJianBean.ListBean listBean) {

        }
    }

    class GifHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageGif;

        //这个 是xutil 的 类
        private ImageOptions imageOptions;

        GifHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            ivImageGif = (ImageView) convertView.findViewById(R.id.iv_image_gif);

            imageOptions = new ImageOptions.Builder()
                    //包裹类型
                    .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, -2)
                    //设置圆角
                    .setRadius(10)
                    .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .build();

        }

        public void setData(BSTuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());

            //下面是gif
            if (mediaItem.getGif() != null && mediaItem.getGif() != null && mediaItem.getGif().getImages() != null) {
//                Glide.with(context).load(mediaItem.getGif().getImages().get(0)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivImageGif);
                x.image().bind(ivImageGif, mediaItem.getGif().getImages().get(0), imageOptions);
            }

        }
    }


    class TextHolder extends BaseViewHolder {
        TextView tvContext;

        TextHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);


        }

        public void setData(BSTuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
        }
    }


    class ImageHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageIcon;

        ImageHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            ivImageIcon = (ImageView) convertView.findViewById(R.id.iv_image_icon);

        }

        public void setData(BSTuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
            //图片特有的
            ivImageIcon.setImageResource(R.drawable.ic_present_5);
            if (mediaItem.getImage() != null && mediaItem.getImage() != null && mediaItem.getImage().getThumbnail_small() != null) {
                Picasso.with(mContext).
                        load(mediaItem.getImage().getThumbnail_small().get(0))
                        .into(ivImageIcon);

            }




        }
    }

    class VideoHoder extends BaseViewHolder {
        atguigu.com.liangcangduanzi.utils.Utils utils;
        TextView tvContext;
        JCVideoPlayerStandard jcvVideoplayer;
        TextView tvPlayNums;
        TextView tvVideoDuration;
        ImageView ivCommant;
        TextView tvCommantContext;

        VideoHoder(View convertView) {
            //这个方法一定要写
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            utils = new atguigu.com.liangcangduanzi.utils.Utils();
            tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
            tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
            ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
            tvCommantContext = (TextView) convertView.findViewById(R.id.tv_commant_context);
            jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
        }

        /**
         * 绑定数据
         *
         * @param mediaItem
         */
        public void setData(BSTuiJianBean.ListBean mediaItem) {
            super.setData(mediaItem);

            //设置文本-所有的都有,只有广告没有哦
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
            //视频特有的------------------------
            //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
            boolean setUp = jcvVideoplayer.setUp(
                    mediaItem.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            //加载图片
            if (setUp) {
//                ImageLoader.getInstance().displayImage(mediaItem.getVideo().getThumbnail().get(0),
//                        jcvVideoplayer.thumbImageView);
                Picasso.
                        with(mContext)
                        .load(mediaItem.getVideo()
                        .getThumbnail().get(0))
                        .into(jcvVideoplayer.thumbImageView);
            }
            tvPlayNums.setText(mediaItem.getVideo().getPlaycount() + "次播放");
            tvVideoDuration.setText(utils.stringForTime(mediaItem.getVideo().getDuration() * 1000) + "");
        }
    }

    class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        //ImageView ivRightMore;
        ImageView ivVideoKind;
        TextView tvVideoKindText;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        LinearLayout llDownload;

        TextView tvDing;
        TextView tvCai;
        TextView tvShare;
        TextView tvpingLun;

        public BaseViewHolder(View convertView) {
            super(convertView);
            //公共的-头部
            ivHeadpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
            //ivRightMore = (ImageView) convertView.findViewById(R.id.iv_right_more);
            //公共部分-bottom
            ivVideoKind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
            tvVideoKindText = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
            tvShenheDingNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) convertView.findViewById(R.id.tv_posts_number);
            llDownload = (LinearLayout) convertView.findViewById(R.id.ll_download);

            tvDing = (TextView) convertView.findViewById(R.id.tv_ding);
            tvCai = (TextView) convertView.findViewById(R.id.tv_cai);
            tvShare = (TextView) convertView.findViewById(R.id.tv_share);
            tvpingLun = (TextView) convertView.findViewById(R.id.tv_pinglun);


            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BSTuiJianBean.ListBean listEntity = datas.get(getLayoutPosition());
                    if(listEntity !=null ){
                        //3.传递url  跳转到 webview
                       // Intent intent = new Intent(mContext,ShowImageAndGifActivity.class);
                        Intent intent = new Intent(mContext, GifAndImageActivity.class);
                        if(listEntity.getType().equals("gif")){
                           // String url = listEntity.getGif().getImages().get(0);
                            String url = datas.get(getLayoutPosition()).getShare_url();
                            intent.putExtra("url",url);
                            mContext.startActivity(intent);
                        }else if(listEntity.getType().equals("image")){
                           // String url = listEntity.getImage().getBig().get(0);
                            String url = datas.get(getLayoutPosition()).getShare_url();
                            intent.putExtra("url",url);
                            mContext.startActivity(intent);
                        }
                    }
                }
            });

            tvDing.setOnClickListener(this);
            tvCai.setOnClickListener(this);
            tvShare.setOnClickListener(this);
            tvpingLun.setOnClickListener(this);

        }

        public void setData(BSTuiJianBean.ListBean mediaItem) {
            if (mediaItem.getU() != null && mediaItem.getU().getHeader() != null && mediaItem.getU().getHeader().get(0) != null) {
                x.image().bind(ivHeadpic, mediaItem.getU().getHeader().get(0));
                Picasso.
                        with(mContext)
                        .load(mediaItem.getU().getHeader().get(0))
                        .transform(new CircleTransform())
                        .into(ivHeadpic);


            }
            if (mediaItem.getU() != null && mediaItem.getU().getName() != null) {
                tvName.setText(mediaItem.getU().getName() + "");
            }

            tvTimeRefresh.setText(mediaItem.getPasstime());

            //设置标签
            List<BSTuiJianBean.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
            if (tagsEntities != null && tagsEntities.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < tagsEntities.size(); i++) {
                    buffer.append(tagsEntities.get(i).getName() + " ");
                }
                tvVideoKindText.setText(buffer.toString());
            }

            //设置点赞，踩,转发

            tvShenheDingNumber.setText(mediaItem.getUp());
            tvShenheCaiNumber.setText(mediaItem.getDown() + "");
            tvPostsNumber.setText(mediaItem.getForward() + "");

        }

        public void setDefult(){
            tvDing.setClickable(false);
            tvCai.setClickable(false);
            tvShare.setClickable(false);
            tvpingLun.setClickable(false);
        };


        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_ding:

                    setDefult();
                    tvDing.setBackgroundResource(R.drawable.shenhe_ding_pic_an);
                    break;
                case R.id.tv_cai:

                    setDefult();
                    tvCai.setBackgroundResource(R.drawable.shenhe_cai_pic_an);
                    break;
                
                case R.id.tv_share:

                    setDefult();
                    tvShare.setBackgroundResource(R.drawable.forward_press);
                    
                    break;

                case R.id.tv_pinglun:

                    setDefult();
                    tvpingLun.setBackgroundResource(R.drawable.commend_press);

                    break;
            }

        }
    }




}
