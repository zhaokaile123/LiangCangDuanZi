package atguigu.com.liangcangduanzi.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.concurrent.TimeUnit;

import atguigu.com.liangcangduanzi.utils.PublicStaticData;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.OkHttpClient;



/**
 * Created by ASUS on 2017/7/6.
 */

public class MyApplication extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
         context = this.getApplicationContext();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

       // ShareSDK.initSDK(this);

        PublicStaticData.myShareSDK = new ShareSDK();
        PublicStaticData.myShareSDK.initSDK(getApplicationContext());


        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

    }
}
