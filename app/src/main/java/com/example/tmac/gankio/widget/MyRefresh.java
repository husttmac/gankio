package com.example.tmac.gankio.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

import com.example.tmac.gankio.R;

/**
 * Created by Tmac on 2016/11/24.
 */

public class MyRefresh extends SwipeRefreshLayout {
    public ListView mListView;
    private OnLoadMoreListener loadMoreListener;
    private View mListViewFooter;
    private boolean isLoading = false;
    private int down;
    private int last;
    private int touchSlop;


    public MyRefresh(Context context) {
        this(context,null);
    }

    public MyRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        mListViewFooter = LayoutInflater.from(context).inflate(
                R.layout.listview_footer, null, false);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }



    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);
        } else {
            mListView.removeFooterView(mListViewFooter);
            down = 0;
            last = 0;
        }
    }

    public void setOnLoadListener(OnLoadMoreListener loadListener) {
        loadMoreListener = loadListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                down = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                last = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if(isNeedLoadMore()){
                    setLoading(true);
                    this.loadMoreListener.onLoadMore();
                    return true;
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public boolean isNeedLoadMore(){
        boolean isBottom = false;
        if(mListView != null && mListView.getAdapter()!= null){
            isBottom = (mListView.getAdapter().getCount() -1== mListView.getLastVisiblePosition());
        }
        return (loadMoreListener != null) && !isLoading && isBottom && (down - last >= touchSlop);
    }


}
