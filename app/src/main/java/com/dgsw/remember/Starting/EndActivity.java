package com.dgsw.remember.Starting;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.dgsw.remember.R;
import com.dgsw.remember.SlideAdapter.SlidingAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class EndActivity extends AppCompatActivity {

    SliderView imgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end);

        imgs = (SliderView)findViewById(R.id.imgs);

        imgs.setSliderAdapter(new SlidingAdapter(getApplicationContext()));
        imgs.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imgs.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imgs.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imgs.setIndicatorSelectedColor(Color.WHITE);
        imgs.setIndicatorUnselectedColor(Color.GRAY);
        imgs.setScrollTimeInSec(3); //set scroll delay in seconds :
        imgs.startAutoCycle();

        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scroll));

            scrollview.fullScroll(ScrollView.FOCUS_UP);
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofInt(scrollview, "scrollY", scrollview.getBottom()+100).setDuration(80000).start();

            }
        });
    }
}
