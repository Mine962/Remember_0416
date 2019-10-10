package com.dgsw.remember.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) // 해당 시간대 조건이 맞으면 이 함수를 자동 실행함. 홈키로 나가져도 실행됨.

    {

        Toast.makeText(context,"특정 시간대 조건이 맞아 시작합니다. ", Toast.LENGTH_SHORT).show();

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

        vibrator.vibrate(1000);


    }

}