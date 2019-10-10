package com.dgsw.remember;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgsw.remember.InfoClass.infoAdapter;
import com.dgsw.remember.InfoClass.infoData;
import com.dgsw.remember.SlideAdapter.SlideAdapter;
import com.dgsw.remember.SlideAdapter.SlidingAdapter;
import com.dgsw.remember.SlideAdapter.SlidingAnother;
import com.dgsw.remember.Write_0416.WriteActivity;
import com.dgsw.remember.Starting.EndActivity;
import com.dgsw.remember.Starting.LoadingActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.txusballesteros.bubbles.BubblesManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton3;
    infoAdapter adapter;

    BubblesManager bubblesManager;
    ArrayList<infoData> mListData = new ArrayList<>();
    ImageView imv;
    CheckBox accept;
    Calendar mCalendar;
    LinearLayout linear, content;
    CardView active, card_view;
    int count, pos = 0 , type = 0;
    String user_name, image_url, email;
    MediaPlayer musicPlayer;
    ImageView image;
    TextView text1, text2, text3, text4;
    ListView infoList;
    boolean firstrun , btn;
    private CountDownTimer _timer;
    SliderView sliderView, imgs;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), LoadingActivity.class));

        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,4);
        calendar.set(Calendar.DAY_OF_MONTH,16);


        infoList = (ListView) findViewById(R.id.infolist);
        imv = (ImageView) findViewById(R.id.background);
        accept = (CheckBox) findViewById(R.id.accpet);
        linear = (LinearLayout) findViewById(R.id.view_click);
        active = (CardView) findViewById(R.id.active);
        card_view = (CardView) findViewById(R.id.card_view);
        text1 = (TextView) findViewById(R.id.text4);
        text2 = (TextView) findViewById(R.id.text5);
        text3 = (TextView) findViewById(R.id.text6);
        text4 = (TextView) findViewById(R.id.text7);
        image = (ImageView) findViewById(R.id.image1);
        firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("FirstState", true);
        final Animation animation_visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible);
        final Animation animation_visible2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vis);
        final Animation animation_invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible);

        adapter = new infoAdapter(this, mListData);
        infoList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        bubblesManager = new BubblesManager.Builder(this)
                .build();

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_new,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat("MM월 dd일");
        // nowDate 변수에 값을 저장한다.
        String formatDate = sdfNow.format(date);

        TextView text = (TextView) layout.findViewById(R.id.text);

        if(formatDate.contains("04월 16일")){
            text.setText("오늘은 세월호 사건이 일어난 날입니다.\n\n오늘을 꼭 기억해주세요.");
        }else{
            text.setText("꼭 기억해주세요. 세월호 사건은 4월 16일입니다.\n\n오늘은 "+formatDate+"입니다.");

        }
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();


        musicPlayer = MediaPlayer.create(MainActivity.this, R.raw.music_sorry_0416);
        musicPlayer.start();
        musicPlayer.setLooping(true);

        sliderView = (SliderView) findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new SlideAdapter(getApplicationContext()));

        sliderView.setIndicatorAnimation(IndicatorAnimations.DROP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(10); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        content = (LinearLayout) findViewById(R.id.linear_content);




        infoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                String get_title = mListData.get(position).getInfoName();

                if (get_title.contains("사건")) {
                    case_view();
                }else if(get_title.contains("위인")) {

                    case_do();
                }else{
                    Toast.makeText(getApplicationContext(), "오류_001 : 리스트 파악 불가", Toast.LENGTH_SHORT).show();
                }

            }
        });




        if (!firstrun) {
            active.setVisibility(View.GONE);
            card_view.setVisibility(View.VISIBLE);
            infoList.setVisibility(View.VISIBLE);
        }


        _timer = new CountDownTimer(10 * 500, 500) {   //_timer 객체에 10*1000 밀리초(10초) 가 1000밀리초마다 1씩달게한다.

            public void onTick(long millisUntilFinished) {
                //do nothing in this method :)
            }

            public void onFinish() {

                count++;
                if (count == 1) {
                    imv.startAnimation(animation_visible);
                    imv.setVisibility(View.INVISIBLE);
                    imv.setBackground(getResources().getDrawable(R.drawable.bg_sewol));
                    imv.startAnimation(animation_invisible);
                    imv.setVisibility(View.VISIBLE);
                } else if (count == 2) {
                    imv.startAnimation(animation_visible);
                    imv.setVisibility(View.INVISIBLE);
                    imv.setBackground(getResources().getDrawable(R.drawable.write_ground));
                    imv.startAnimation(animation_invisible);
                    imv.setVisibility(View.VISIBLE);
                } else {
                    count = 0;
                }

                _timer.cancel();
                _timer.start();

            }

        };

        _timer.start();

        mListData.add(new infoData("세월호 사건", "세월호 사건은 2014년 4월 16일 단원고 학생들에게 일어난 끔찍한 비극입니다.", R.drawable.information, "04월 16일"));
        mListData.add(new infoData("세월호의 위인분들..", "끝까지 단원고 학생분들을 구조하시다가 돌아가신분들 아홉 분입니다.", R.drawable.information, "04월 16일"));
        adapter.notifyDataSetChanged();
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                            if (firstrun) {
                                if (accept.isChecked()) {
                                    long now = System.currentTimeMillis();
                                    // 현재시간을 date 변수에 저장한다.
                                    Date date = new Date(now);
                                    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                    // nowDate 변수에 값을 저장한다.
                                    String formatDate = sdfNow.format(date);


                                    Toast.makeText(getApplicationContext(), formatDate + " , 경고문 처리에 동의하셨습니다.", Toast.LENGTH_SHORT).show();
                                    preview();

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            active.startAnimation(animation_visible2);
                                            active.setVisibility(View.GONE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    card_view.startAnimation(animation_visible);
                                                    card_view.setVisibility(View.VISIBLE);
                                                    infoList.startAnimation(animation_visible);
                                                    infoList.setVisibility(View.VISIBLE);
                                                }
                                            }, 1000);

                                        }
                                    }, 2000);


                                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                                            .edit()
                                            .putBoolean("FirstState", false)
                                            .commit();

                                } else {

                                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                                            .edit()
                                            .putBoolean("FirstState", true)
                                            .commit();
                                }

                            } else {
                                card_view.setVisibility(View.VISIBLE);
                                infoList.setVisibility(View.VISIBLE);
                            }

            }
        });
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);

        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), WriteActivity.class));
                overridePendingTransition(R.anim.visibler, R.anim.vis);
                materialDesignFAM.close(true);
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                        startActivity(new Intent(getApplicationContext(), EndActivity.class));
                        overridePendingTransition(R.anim.visibler, R.anim.vis);

                materialDesignFAM.close(true);

            }
        });

    }

    void case_do(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.case_do, null);
        builder.setCancelable(false);
        builder.setView(view);

        imgs = (SliderView) view.findViewById(R.id.imgs);

        Button ok = (Button) view.findViewById(R.id.ok);

        imgs.setSliderAdapter(new SlidingAdapter(getApplicationContext()));
        imgs.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imgs.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imgs.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imgs.setIndicatorSelectedColor(Color.WHITE);
        imgs.setIndicatorUnselectedColor(Color.GRAY);
        imgs.setScrollTimeInSec(3); //set scroll delay in seconds :
        imgs.startAutoCycle();

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();

            }


        });

        dialog.show();
    }
    void case_view() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.case_view, null);
        builder.setCancelable(false);
        builder.setView(view);

        final TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("세월호 사건");
        final Button ok = (Button) view.findViewById(R.id.ok);
        final WebView web = (WebView) view.findViewById(R.id.web_);

        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://namu.wiki/w/%EC%B2%AD%ED%95%B4%EC%A7%84%ED%95%B4%EC%9A%B4%20%EC%84%B8%EC%9B%94%ED%98%B8%20%EC%B9%A8%EB%AA%B0%20%EC%82%AC%EA%B3%A0");
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }


        });

        dialog.show();


    }



    void preview() {
        final Animation animation_visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible);
        final Animation animation_visible2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vis);
        final Animation animation_invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                content.startAnimation(animation_visible);
                content.setVisibility(View.VISIBLE);

            }
        }, 3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                image.startAnimation(animation_visible);
                image.setVisibility(View.VISIBLE);

                text1.startAnimation(animation_visible);
                text1.setVisibility(View.VISIBLE);

                text2.startAnimation(animation_visible);
                text2.setVisibility(View.VISIBLE);

                text3.startAnimation(animation_visible);
                text3.setVisibility(View.VISIBLE);

                text4.startAnimation(animation_visible);
                text4.setVisibility(View.VISIBLE);


            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                content.startAnimation(animation_visible2);
                content.setVisibility(View.INVISIBLE);

            }
        }, 15000);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.turn_off, null);
        builder.setCancelable(false);
        builder.setView(view);

        imgs = (SliderView) view.findViewById(R.id.imgs);

        Button ok = (Button) view.findViewById(R.id.ok);

        Button not = (Button) view.findViewById(R.id.not);
        imgs.setSliderAdapter(new SlidingAnother(getApplicationContext()));
        imgs.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imgs.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imgs.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imgs.setIndicatorSelectedColor(Color.WHITE);
        imgs.setIndicatorUnselectedColor(Color.GRAY);
        imgs.setScrollTimeInSec(3); //set scroll delay in seconds :
        imgs.startAutoCycle();

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        not.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();

            }


        });



        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                finish();

            }


        });

        dialog.show();
    }



    public void onDestroy() {
        musicPlayer.stop();

        super.onDestroy();



    }
}
