package atguigu.com.bilibili.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/6.
 */


//0kHttp 的工具类
public class NetUtils {


    //单例
    private NetUtils(){};

    private static NetUtils netUtils = new NetUtils();

    public static NetUtils getInstance(){
        return  netUtils;
    }

    // get 方法
    public void get(String url, final OnOkHttpListener listener){
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if(listener != null) {
                            listener.onError(call,e,id);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(listener != null) {
                            listener.onResponse(response,id);
                        }
                    }
                });


    }


    //设置接口
    public interface OnOkHttpListener{
        void onResponse(String response, int id);

        void onError(Call call, Exception e, int id);
    }


}
