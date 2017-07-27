package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import atguigu.com.liangcangduanzi.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity {


    @InjectView(R.id.iv_icon)
    ImageView ivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);

       /* AnimationDrawable animation = (AnimationDrawable) imIcon.getBackground();
        animation.start();*/

        Glide.with(this).load(R.drawable.loading_start).asGif().into(ivIcon);

        handler.sendEmptyMessageDelayed(0,4000);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    };
}
