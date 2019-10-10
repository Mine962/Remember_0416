package com.dgsw.remember.Starting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgsw.remember.R;

public class LoadingActivity extends AppCompatActivity {

    ImageView img;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);

        final Animation scale_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loading);
        final Animation visble = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible);

        img = (ImageView)findViewById(R.id.scale);
        tv = (TextView)findViewById(R.id.animated);
        tv.startAnimation(visble);
        img.startAnimation(scale_up);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 6000);
    }
}
