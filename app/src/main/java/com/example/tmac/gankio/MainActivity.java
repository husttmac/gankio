package com.example.tmac.gankio;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.tmac.gankio.bean.AndroidBean;
import com.example.tmac.gankio.widget.MyRefresh;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
//    private PagerTabStrip tabStrip;
    final String [] titles = {"Android","iOS","前端"};
    final List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragments.add(new AndroidFragment());
        fragments.add(new IOSFragment());
        fragments.add(new WebFragment());
        viewPager.setAdapter(adapter);
    }





    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    };


}
