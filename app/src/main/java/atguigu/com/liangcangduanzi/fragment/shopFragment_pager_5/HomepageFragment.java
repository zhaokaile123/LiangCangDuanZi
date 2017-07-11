package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.HomeAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.HomeBean1;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/5.
 */

public class HomepageFragment extends BaseFragment {

    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    private HomeAdapter adapter;

    private ImageView iv_go_to_top;
    private HomeBean1 homeBean1;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.pager_home, null);
        ButterKnife.inject(this, view);

        iv_go_to_top = (ImageView) view.findViewById(R.id.iv_go_to_top);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(JieKouUtils.HOME)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressData(response);

                    }
                });
    }

    private List<HomeBean1.DataBean.ItemsBean.ListBeanX> list;
    private void progressData(String json) {

        homeBean1 = new Gson().fromJson(json, HomeBean1.class);

        list = homeBean1.getData().getItems().getList();

        adapter = new HomeAdapter(getActivity(),list);

        recycleview.setAdapter(adapter);

        // 设置布局管理器 以及 显示 回到顶部图片出现的位置
        GridLayoutManager liner = new GridLayoutManager(context,1);
        liner.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position <= 3){
                    //隐藏
                    iv_go_to_top.setVisibility(View.GONE);
                }else{
                    //显示
                    iv_go_to_top.setVisibility(View.VISIBLE);
                }
                return 1;
            }
        });

        recycleview.setLayoutManager(liner);

        initListener();

    }


    private void initListener() {

        iv_go_to_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycleview.scrollToPosition(0);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
