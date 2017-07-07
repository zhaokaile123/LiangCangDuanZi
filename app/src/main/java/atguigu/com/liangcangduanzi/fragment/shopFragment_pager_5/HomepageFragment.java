package atguigu.com.liangcangduanzi.fragment.shopFragment_pager_5;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.adapter.HomeAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.HomeBean;
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
    private HomeBean homeBean;

    private ImageView iv_go_to_top;

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

    List<HomeBean.DataBean.ItemsBean.ListBean> list;

    private void progressData(String json) {

        homeBean = new Gson().fromJson(json, HomeBean.class);

        list = homeBean.getData().getItems().getList();

        adapter = new HomeAdapter(getActivity(),list);

        recycleview.setAdapter(adapter);

        recycleview.setLayoutManager(new LinearLayoutManager(context));

        initListener();

    }


    private void initListener() {
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                Log.e("TAG","HomepagerFragment ==  还没做" );


               /* //String content = homeBean.getData().getItems().get(position).getTopic_name();
                Intent intent = new Intent(getActivity(), Special_WebViewActivity.class);
                intent.putExtra("url",url);
                //intent.putExtra("content",content);
                startActivity(intent);
*/
            }
        });


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
