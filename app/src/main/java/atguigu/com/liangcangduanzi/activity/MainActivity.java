package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import atguigu.com.liangcangduanzi.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_type)
    RadioButton rbType;
    @InjectView(R.id.rb_community)
    RadioButton rbCommunity;
    @InjectView(R.id.rb_cart)
    RadioButton rbCart;
    @InjectView(R.id.rb_user)
    RadioButton rbUser;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


    }
}
