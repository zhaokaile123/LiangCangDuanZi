package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import atguigu.com.liangcangduanzi.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.serach_iv_search)
    ImageView serachIvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.iv_back, R.id.serach_iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.serach_iv_search:
                Toast.makeText(SearchActivity.this, "我在SearchActivity中，来设置我吧", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
