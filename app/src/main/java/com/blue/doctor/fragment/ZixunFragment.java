package com.blue.doctor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue.doctor.R;
import com.blue.doctor.adaptor.ZixunAdaptor;
import com.blue.doctor.base.BaseFragment;
import com.blue.doctor.bean.Zixun;
import com.blue.doctor.cache.CacheManager;
import com.blue.doctor.plugin.LoadListView;
import com.blue.doctor.test.ImageTest;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/10
 */
public class ZixunFragment extends BaseFragment implements LoadListView.ILoadInterface{
    @Bind(R.id.zixun_listview)
    LoadListView listView;
    private ArrayList<Zixun> list;
    ZixunAdaptor adaptor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zixun, null);
        ButterKnife.bind(this, view);
        getData();
        adaptor = new ZixunAdaptor(list,getActivity());
        listView.setInterface(this);
        listView.setAdapter(adaptor);
        return view;
    }

    private List<Zixun> getData() {
        list=new ArrayList<>();
        for (int i = 0; i < ImageTest.imageThumbUrls.length; i++) {
            Zixun zixun = new Zixun();
            zixun.setTitle("nihao---"+i);
            zixun.setImageUrl(ImageTest.imageThumbUrls[i]);
            list.add(zixun);

        }
        return list;
    }
    private List<Zixun> getMoreData() {

        for (int i = 0; i < ImageTest.imageThumbUrls.length; i++) {
            Zixun zixun = new Zixun();
            zixun.setTitle("nihaoallen---"+i);
            zixun.setImageUrl(ImageTest.imageThumbUrls[i]);
            list.add(zixun);

        }
        return list;
    }
    @Override
    public void onLoad() {
        getMoreData();
        showListData();

    }

    private void showListData(){
        if(adaptor==null){
            adaptor = new ZixunAdaptor(list,getActivity());
            listView.setInterface(this);
            listView.setAdapter(adaptor);
        }else{
            adaptor.setNoticefyChange(list);
            listView.loadComplete();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager cm=CacheManager.getInstance();
        cm.fluchCache();
        cm.cancelAllTasks();
    }
}
