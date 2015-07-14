package com.blue.doctor.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.blue.doctor.cache.CacheManager;
import com.blue.doctor.cache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/11
 */
public class BitmapWorkerTask extends AsyncTask<Object, Void, Bitmap> {

    private String imageUrl;
    private DiskLruCache mDiskLruCache;
    private Set<BitmapWorkerTask> taskCollection;
    private Map<String,ImageView> views;

    @Override
    protected Bitmap doInBackground(Object... params) {
        imageUrl = (String)params[0];
        mDiskLruCache=(DiskLruCache)params[1];
        views=(Map<String,ImageView>)params[2];
        taskCollection=(Set<BitmapWorkerTask>)params[3];
        FileDescriptor fileDescriptor = null;
        FileInputStream fileInputStream = null;
        DiskLruCache.Snapshot snapShot = null;
        try {
            // 生成图片URL对应的key
            final String key = CacheManager.getInstance().hashKeyForDisk(imageUrl);
            // 查找key对应的缓存
            snapShot = mDiskLruCache.get(key);
            if (snapShot == null) {
                // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                if (editor != null) {
                    OutputStream outputStream = editor.newOutputStream(0);
                    if (downloadUrlToStream(imageUrl, outputStream)) {
                        editor.commit();
                    } else {
                        editor.abort();
                    }
                }
                // 缓存被写入后，再次查找key对应的缓存
                snapShot = mDiskLruCache.get(key);
            }
            if (snapShot != null) {
                fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                fileDescriptor = fileInputStream.getFD();
            }
            // 将缓存数据解析成Bitmap对象
            Bitmap bitmap = null;
            if (fileDescriptor != null) {
                bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            }
            if (bitmap != null) {
                // 将Bitmap对象添加到内存缓存当中
                CacheManager.getInstance().addBitmapToMemoryCache(imageUrl, bitmap);
            }
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileDescriptor == null && fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
        // ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
        ImageView imageView=views.get(imageUrl);
        if (imageView != null && imageView.getTag() ==imageUrl) {
            imageView.setImageBitmap(bitmap);
        }
        taskCollection.remove(this);
    }


    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {

        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
