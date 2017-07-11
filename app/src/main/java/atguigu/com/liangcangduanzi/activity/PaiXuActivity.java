package atguigu.com.liangcangduanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PaiXuActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.search)
    ImageView search;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_moren)
    TextView tvMoren;
    @InjectView(R.id.tv_tuijian_more)
    TextView tvTuijianMore;
    @InjectView(R.id.tv_huanying)
    TextView tvHuanying;
    @InjectView(R.id.tv_tuijian_new)
    TextView tvTuijianNew;
    @InjectView(R.id.tv_in_new)
    TextView tvInNew;
    @InjectView(R.id.activity_pai_xu)
    LinearLayout activityPaiXu;

    private LinearLayout ll_null;

    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_xu);
        ButterKnife.inject(this);

        ll_null = (LinearLayout) findViewById(R.id.ll_null);


        initListener();

    }

    private void initListener() {
        tvMoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaiXuActivity.this, "000", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(PaiXuActivity.this, MainActivity.class);
                intent.putExtra("aaa", 1);
                startActivity(intent);
                finish();
            }
        });

        tvTuijianMore.setOnClickListener(this);
        tvHuanying.setOnClickListener(this);
        tvTuijianNew.setOnClickListener(this);
        tvInNew.setOnClickListener(this);

        ll_null.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @OnClick({R.id.search, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tuijian_more:
                url = JieKouUtils.MORE_TUIJIAN;

                break;
            case R.id.tv_huanying:
                url = JieKouUtils.HUANYING;


                break;
            case R.id.tv_tuijian_new:
                url = JieKouUtils.TUIJIAN_NEW;


                break;
            case R.id.tv_in_new:
                url = JieKouUtils.IN_NEW;

                break;
        }


        Intent intent = new Intent(PaiXuActivity.this, DarenActivity2.class);
        intent.putExtra("url", url);
        startActivity(intent);
        finish();


    }


}
