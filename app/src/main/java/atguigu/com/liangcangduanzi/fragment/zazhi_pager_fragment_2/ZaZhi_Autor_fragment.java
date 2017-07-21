package atguigu.com.liangcangduanzi.fragment.zazhi_pager_fragment_2;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import atguigu.com.liangcangduanzi.R;
import atguigu.com.liangcangduanzi.activity.ZaZhiItemActivity;
import atguigu.com.liangcangduanzi.adapter.ZaZhiAutorAdapter;
import atguigu.com.liangcangduanzi.base.BaseFragment;
import atguigu.com.liangcangduanzi.bean.ZaZhiAutorBean;
import atguigu.com.liangcangduanzi.utils.JieKouUtils;
import atguigu.com.liangcangduanzi.utils.NetUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/13.
 */

public class ZaZhi_Autor_fragment extends BaseFragment {

    @InjectView(R.id.pull_to_refresh_recycleView)
    PullToRefreshRecyclerView pullToRefreshRecycleView;

    private RecyclerView recyclerView;
    private ZaZhiAutorBean zaZhiAutorBean;
    private ZaZhiAutorAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_zazhi_autor, null);
        ButterKnife.inject(this, view);

        recyclerView = pullToRefreshRecycleView.getRecyclerView();
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        NetUtils.getInstance().get(JieKouUtils.AUTOR, new NetUtils.OnOkHttpListener() {
            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

    private void processData(String json) {
        zaZhiAutorBean = new Gson().fromJson(json, ZaZhiAutorBean.class);
        adapter = new ZaZhiAutorAdapter(context,zaZhiAutorBean.getData().getItems());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        iniListener();
    }

    private void iniListener() {

        adapter.setOnItemClickListener(new ZaZhiAutorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, ZaZhiItemActivity.class);

                int author_id = Integer.parseInt(zaZhiAutorBean.getData().getItems().get(position).getAuthor_id());

                String author_name = zaZhiAutorBean.getData().getItems().get(position).getAuthor_name();
                intent.putExtra("author_id",author_id);
                intent.putExtra("author_name",author_name);
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.anim_in_top,R.anim.anim_out_bottom);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
