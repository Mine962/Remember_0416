package com.dgsw.remember.Write_0416;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgsw.remember.MainActivity;
import com.dgsw.remember.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    GridView androidGridView;
    ArrayList<Data_write> sorry = new ArrayList<>();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ReadAdapter adapter;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton2;
    TextView data_value;
    CountDownTimer _timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read);

        adapter = new ReadAdapter(this, sorry);

        data_value = (TextView) findViewById(R.id.data_value);
        androidGridView = (GridView) findViewById(R.id.gridview);
        androidGridView.setAdapter(adapter);
        _timer = new CountDownTimer(10 * 1000, 1000) {   //_timer 객체에 10*1000 밀리초(10초) 가 1000밀리초마다 1씩달게한다.

            public void onTick(long millisUntilFinished) {

                FirebaseDatabase.getInstance().getReference().child("Data_value").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int data = Integer.parseInt(dataSnapshot.getValue().toString());
                        DecimalFormat myFormatter = new DecimalFormat("###,###");
                        String formattedStringPrice = myFormatter.format(data);
                        data_value.setText(formattedStringPrice + "장");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            public void onFinish() {
                _timer.cancel();
                _timer.start();
            }

        };
        _timer.start();

        databaseReference.child("Remember_0416").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Data_write writting = dataSnapshot.getValue(Data_write.class);
                sorry.add(new Data_write(writting.getMessage(), writting.gettime()));
                ScrollBottom();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             finish();
             materialDesignFAM.close(true);
            }
        });
    }

        private void ScrollBottom() {
            androidGridView.post(new Runnable() {
                @Override
                public void run() {
                    androidGridView.setSelection(adapter.getCount() - 1);
                }
            });
        }
    }

