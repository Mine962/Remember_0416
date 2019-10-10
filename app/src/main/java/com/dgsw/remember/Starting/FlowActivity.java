package com.dgsw.remember.Starting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dgsw.remember.R;

public class FlowActivity extends AppCompatActivity {

    ImageView img, img_letter;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flow);

        final Animation animation_visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible);


        img = (ImageView)findViewById(R.id.backg_);
        img_letter = (ImageView)findViewById(R.id.letter);
        txt = (TextView)findViewById(R.id.txt);
        Glide.with(getApplicationContext())
                .load(R.raw.ocean)
                .into(img);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                txt.startAnimation(animation_visible);
                txt.setVisibility(View.VISIBLE);
                img_letter.startAnimation(animation_visible);
                img_letter.setVisibility(View.VISIBLE);

            }
        }, 2300);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                finish();

            }
        }, 5500);
    }
}
