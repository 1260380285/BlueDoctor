package com.blue.doctor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapCallBack;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WelcomeActivity extends Activity implements Animation.AnimationListener{
    @Bind(R.id.image)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);
        startAnimate();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {}

    @Override
    public void onAnimationStart(Animation animation) {}

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startAnimate(){
        Animation animation=AnimationUtils.loadAnimation(this, R.anim.welcome_animate);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        animation.setAnimationListener(this);
        imageview.setAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        //EventBus.getDefault().unregister(this);
    }
}
