package atguigu.com.liangcangduanzi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ShouHouActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.search)
    ImageView search;
    @InjectView(R.id.activity_shou_hou)
    LinearLayout activityShouHou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_hou);
        ButterKnife.inject(this);

        initData();
    }

    private void initData() {
        tv.setText("没有售后");
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(50);
    }


    @OnClick(R.id.search)
    public void onViewClicked() {
        finish();
    }
}
