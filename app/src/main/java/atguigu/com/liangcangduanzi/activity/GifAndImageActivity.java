package atguigu.com.liangcangduanzi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import atguigu.com.liangcangduanzi.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class GifAndImageActivity extends AppCompatActivity {

    @InjectView(R.id.webview)
    WebView webview;

    private String url;
    private WebSettings settings;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_and_image);
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

    }

    private void initListerner() {}

}
