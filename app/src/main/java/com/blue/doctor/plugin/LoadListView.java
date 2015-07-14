package com.blue.doctor.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.blue.doctor.R;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/11
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener {
    private static final String TAG = LoadListView.class.getSimpleName();
    private int totalItem;
    private int lastItem;
    boolean isLoading = false;
    ILoadInterface iLoadInterface;
    View footer;
    View header;

    public LoadListView(Context context) {
        super(context);
        initHeader(context);
        initFooter(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeader(context);
        initFooter(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeader(context);
        initFooter(context);
    }

    private void initHeader(Context context) {
        header = View.inflate(context, R.layout.top_image, null);
        this.addHeaderView(header);
    }

    private void initFooter(Context context) {
        footer = View.inflate(context, R.layout.listview_foot, null);
        footer.setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItem == lastItem && scrollState == SCROLL_STATE_IDLE) {
            Log.i(TAG, scrollState + ";" + SCROLL_STATE_IDLE);
            if (!isLoading) {
                // Log.i(TAG,scrollState+"==;"+SCROLL_STATE_IDLE);
                isLoading = true;
                footer.setVisibility(View.VISIBLE);
                iLoadInterface.onLoad();
            }

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastItem = firstVisibleItem + visibleItemCount;
        this.totalItem = totalItemCount;
        //Log.i(TAG,lastItem+";"+totalItem);
    }


    public void loadComplete() {
        isLoading = false;
        footer.setVisibility(View.GONE);
    }

    public void setInterface(ILoadInterface iLoadInterface) {
        this.iLoadInterface = iLoadInterface;
    }

    public interface ILoadInterface {
        void onLoad();
    }
}
