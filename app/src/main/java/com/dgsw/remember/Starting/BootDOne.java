package com.dgsw.remember.Starting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootDOne extends BroadcastReceiver {
    Context mContext;
    public BootDOne (Context context){
        this.mContext = context;
    }
    @Override

    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

        }
    }
}