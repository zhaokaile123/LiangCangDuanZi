package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;


public class GoodsInfoActivity extends AppCompatActivity {

    private int id;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        id = getIntent().getIntExtra("id",-1);
        url = JieKouUtils.GOODSINFOHEAD + id + JieKouUtils.GOODSINFOEND;

        Log.e("TAG",""+url);


    }
}
