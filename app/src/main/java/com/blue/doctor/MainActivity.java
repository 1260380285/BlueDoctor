package com.blue.doctor;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blue.doctor.constant.Constant;
import com.blue.doctor.fragment.KongzhongFragment;
import com.blue.doctor.fragment.WendaFrgment;
import com.blue.doctor.fragment.ZhanghaoFragment;
import com.blue.doctor.fragment.ZixunFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    @Bind(R.id.kongzhonghospital_button)
    Button konghospital_button;
    @Bind(R.id.zixun_button)
    Button zixun_button;
    @Bind(R.id.wenda_button)
    Button wenda_button;
    @Bind(R.id.zhanghao_button)
    Button zhanghao_button;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.title)
    TextView title;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentPagerAdapter mAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initButton();
        initAdaptor();
        viewPager.setAdapter(mAdaptor);
        setPagerListener();

    }

    private void setPagerListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //Log.i(TAG, i + ";" + v + ";" + i2);
            }

            @Override
            public void onPageSelected(int i) {
                Log.i(TAG, i + ";onPageSelected");
                resetButton();
                setCurrentPage(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //Log.i(TAG,i+"onPageScrollStateChanged;");
            }
        });
        getTabDrawable(R.mipmap.kongzhongyiyuan_normal, konghospital_button);
        konghospital_button.setTextColor(getResources().getColor(R.color.lightblue));
    }

    private void resetButton() {
        getTabDrawable(R.mipmap.kongzhongyiyuan_unpress, konghospital_button);
        getTabDrawable(R.mipmap.wenda_unpress, wenda_button);
        getTabDrawable(R.mipmap.zixun_unpress, zixun_button);
        getTabDrawable(R.mipmap.zhanghao_unpress, zhanghao_button);
    }

    private void getTabDrawable(int resourceId, Button button) {
        Drawable drawable = getResources().getDrawable(resourceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        button.setCompoundDrawables(null, drawable, null, null);
        button.setTextColor(getResources().getColor(R.color.lightblack));
    }

    private void setCurrentPage(int i) {
        switch (i) {
            case Constant.KONGZHONGYIYUAN:
                getTabDrawable(R.mipmap.kongzhongyiyuan_normal, konghospital_button);
                setTitleAndButton(R.string.kongzhonghospital_button,konghospital_button);
                break;
            case Constant.WENDA:
                getTabDrawable(R.mipmap.wenda_normal, wenda_button);
                setTitleAndButton(R.string.wenda_button,wenda_button);
                break;
            case Constant.ZIXUN:
                getTabDrawable(R.mipmap.zixun_normal, zixun_button);
                setTitleAndButton(R.string.zixun_button,zixun_button);
                break;
            case Constant.ZHANGHAO:
                getTabDrawable(R.mipmap.zhanghao_normal, zhanghao_button);
                setTitleAndButton(R.string.zhanghao_button,zhanghao_button);
                break;
        }
    }
    private void setTitleAndButton(int ResourceId,Button button){
        title.setText(getResources().getString(ResourceId));
        button.setTextColor(getResources().getColor(R.color.lightblue));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kongzhonghospital_button:
                viewPager.setCurrentItem(Constant.KONGZHONGYIYUAN, false);
                setTitleAndButton(R.string.kongzhonghospital_button,konghospital_button);
                break;
            case R.id.wenda_button:
                viewPager.setCurrentItem(Constant.WENDA, false);
                setTitleAndButton(R.string.wenda_button,wenda_button);
                break;
            case R.id.zixun_button:
                viewPager.setCurrentItem(Constant.ZIXUN, false);
                setTitleAndButton(R.string.zixun_button,zixun_button);
                break;
            case R.id.zhanghao_button:
                viewPager.setCurrentItem(Constant.ZHANGHAO, false);
                setTitleAndButton(R.string.zhanghao_button,zhanghao_button);
                break;
        }
    }

    private void initAdaptor() {
        mAdaptor = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }
        };
    }

    private void initFragment() {
        KongzhongFragment kongzhongFragment = new KongzhongFragment();
        WendaFrgment wendaFrgment = new WendaFrgment();
        ZixunFragment zixunFragment = new ZixunFragment();
        ZhanghaoFragment zhanghaoFragment = new ZhanghaoFragment();
        fragments.add(kongzhongFragment);
        fragments.add(wendaFrgment);
        fragments.add(zixunFragment);
        fragments.add(zhanghaoFragment);
    }

    private void initButton() {
        konghospital_button.setOnClickListener(this);
        wenda_button.setOnClickListener(this);
        zixun_button.setOnClickListener(this);
        zhanghao_button.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        //  EventBus.getDefault().unregister(this);
    }
}
