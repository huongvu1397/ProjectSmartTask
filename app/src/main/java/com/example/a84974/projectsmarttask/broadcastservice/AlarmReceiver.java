package com.example.a84974.projectsmarttask.broadcastservice;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.a84974.projectsmarttask.MainActivity;
import com.example.a84974.projectsmarttask.R;
import com.example.a84974.projectsmarttask.saukhidangnhap;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    // quan li thong bao huy thong bao
    private NotificationManager manager;
    //xay dung cac noi dung thong bao
    private NotificationCompat.Builder builder;
    private String tenbang;

    public AlarmReceiver() {
    }

    // nhận các intent từ hệ thống cần đăng ký trong manifest
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("NhanBR","XinChao");
        tenbang = intent.getStringExtra("tenBang");
        Log.e("NhanBR","XinChao"+tenbang);
        showNotification(context, "Thông báo","Bạn có một công việc đã hết hạn");


    }

    private void showNotification(Context context, String msg, String msgText) {
        //khoi tao notification manager
        manager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context,"channelID");
        //thiet lap thong tin icon title mesage action
        builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
        builder.setContentTitle(msg);
        builder.setContentText(msgText);

        // muon thong bao co cau hinh giong tin nhan SMS
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setPriority(1);
        // thiet lap action khi click de xem chi tiet
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, saukhidangnhap.class), PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(contentIntent);

        manager.notify(1368,builder.build());


    }


}
