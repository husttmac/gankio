package com.example.tmac.gankio;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.tmac.gankio.bean.AndroidBean;
import com.example.tmac.gankio.widget.MyRefresh;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Tmac on 2016/11/24.
 */

public class AndroidFragment extends Fragment implements MyRefresh.OnLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{
    private MyRefresh myRefresh;
    MyAdapter baseAdapter  =new MyAdapter();
    private int page =1;
    private long deltaTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_android,null);
        myRefresh = (MyRefresh) view.findViewById(R.id.myRefresh);
        myRefresh.mListView = (ListView) view.findViewById(R.id.listview);
        myRefresh.setOnLoadListener(this);
        myRefresh.setOnRefreshListener(this);
        myRefresh.mListView.setAdapter(baseAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData(Constants.URL+page);
    }

    public void getData(String url){
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("gankio","onError--"+e.toString());
                myRefresh.setLoading(false);
                myRefresh.setRefreshing(false);
                deltaTime = System.currentTimeMillis();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("gankio","onResponse--");
                deltaTime = System.currentTimeMillis();
                myRefresh.setRefreshing(false);
                try {
                    myRefresh.setLoading(false);
                    JSONObject jsonObject = new JSONObject(response);
                    String str =jsonObject.optString("results");
                    List<AndroidBean> lists = JSON.parseArray(str,AndroidBean.class);
                    baseAdapter.setData(lists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public void onLoadMore() {
        page++;
        getData(Constants.URL+page);
    }

    @Override
    public void onRefresh() {
        Log.d(Constants.TAG,"refresh---");
        if(System.currentTimeMillis()-deltaTime >3000){
            page =1 ;
            getData(Constants.URL+page);
        }
    }
}
