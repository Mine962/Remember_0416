package com.dgsw.remember.Write_0416;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgsw.remember.Starting.FlowActivity;
import com.dgsw.remember.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    FloatingActionButton floatingActionButton1,floatingActionButton2;
    EditText ed_cheer;
    ImageView imv;
    int count = 0;
    boolean firstrun;
    CountDownTimer _timer;
    String content ,times;
    FloatingActionMenu materialDesignFAM;
    SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_write);
        imv = (ImageView) findViewById(R.id.bgs);
        final Animation animation_visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible);
        final Animation animation_invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible);

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        ed_cheer = (EditText)findViewById(R.id.ed_cheer);
        firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("FirstState", true);


        _timer = new CountDownTimer(10 * 500, 500) {   //_timer 객체에 10*1000 밀리초(10초) 가 1000밀리초마다 1씩달게한다.



            public void onTick(long millisUntilFinished) {
                //do nothing in this method :)
            }

            public void onFinish() {

                count++;
                if (count == 1) {
                    imv.startAnimation(animation_visible);
                    imv.setVisibility(View.INVISIBLE);
                    imv.setBackground(getResources().getDrawable(R.drawable.bg_images));
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

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                content = ed_cheer.getText().toString();
                if(content.isEmpty()) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.new_custom,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("내용이 비어있습니다. 최소 한 글자 이상 50자 이하로 작성 부탁드립니다.");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                }else if(content.length() > 50){
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.new_custom,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("30자를 초과하였습니다. 최소 한 글자 이상 50자 이하로 작성 부탁드립니다.");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                }else{
                    long now2 = System.currentTimeMillis();
                    Date date = new Date(now2);
                    SimpleDateFormat CurTimeFormat = new SimpleDateFormat("aa HH:mm");
                    times = CurTimeFormat.format(date);

                    Data_write Data = new Data_write(content,times);
                    databaseReference.child("Remember_0416").push().setValue(Data);
                    FirebaseDatabase.getInstance().getReference().child("Data_value").addListenerForSingleValueEvent(new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                int data = Integer.parseInt(dataSnapshot.getValue().toString());
                                databaseReference.child("Data_value").setValue(data + 1);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    startActivity(new Intent(getApplicationContext(), FlowActivity.class));
                    overridePendingTransition(R.anim.visibler, R.anim.vis);
                    ed_cheer.setText("");
                }
            }
        });
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                materialDesignFAM.close(true);
                startActivity(new Intent(getApplicationContext(), ReadActivity.class));
                overridePendingTransition(R.anim.visibler, R.anim.vis);
            }
        });

    }
    public void onDestroy(){
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("FirstState", false)
                .commit();
        super.onDestroy();
    }
}
