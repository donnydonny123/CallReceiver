package com.example.tsao.callreceiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class callreceiver extends BroadcastReceiver {
    public callreceiver() {
    }

    @SuppressLint("NewApi")
    private void SendNotification(Context context, String number){
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(number+"...");
        builder.setTicker(number+" 來電");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notify = builder.build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notify);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RECEIVER","RECEIVED");
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);//"incoming_nunber"
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);//"state"
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            Toast.makeText(context, number + "來電", Toast.LENGTH_SHORT).show();
            SendNotification(context, number);
        }
    }
}
