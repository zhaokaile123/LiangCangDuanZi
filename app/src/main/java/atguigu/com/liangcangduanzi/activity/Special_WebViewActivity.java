package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import atguigu.com.liangcangduanzi.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class Special_WebViewActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.webview)
    WebView webview;


    private String url;
    private WebSettings settings;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);
        url = getIntent().getStringExtra("url");
        content = getIntent().getStringExtra("content");

        initData();

        initListerner();
    }

    private void initData() {
        settings = webview.getSettings();
        //设置相关配置
        //设置支持javaScript
        settings.setJavaScriptEnabled(true);
        //设置双击页面变大变小
        settings.setUseWideViewPort(true);

        //添加变大变小按钮
        settings.setBuiltInZoomControls(true);

        //加载网页地址
        webview.loadUrl(url.toString());

        tvContent.setText(content);



    }

    private void initListerner() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }


}
