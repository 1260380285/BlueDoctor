package com.blue.doctor.adaptor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.doctor.R;
import com.blue.doctor.bean.Zixun;
import com.blue.doctor.cache.CacheManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/11
 */
public class ZixunAdaptor extends BaseAdapter {

    private ArrayList<Zixun> list;
    private Context context;


    public ZixunAdaptor(ArrayList<Zixun> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setNoticefyChange(ArrayList<Zixun> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Zixun zixun = list.get(position);
        String url = zixun.getImageUrl();
         vh.imageView.setTag(url);
        CacheManager.getInstance().loadBitmaps(vh.imageView, url);
        //vh.imageView.setImageResource(R.mipmap.ic_launcher);
        vh.textView.setText(zixun.getTitle());
        return convertView;
    }

    static class ViewHolder {

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Bind(R.id.image_item)
        ImageView imageView;
        @Bind(R.id.text_item)
        TextView textView;
    }
}
