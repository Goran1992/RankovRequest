package com.example.dev3.notifikacijefragmenti;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.example.dev3.notifikacijefragmenti.R.drawable.logo;

/**
 * Created by dev3 on 7.6.16..
 */
public class myFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG="MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("onMessageReceived", "Pozvana funkcija onMessageReceived");
        Log.d(TAG, "From " + remoteMessage.getFrom());
//        Log.d(TAG, "Body " + remoteMessage.getNotification().getBody());
//        Log.d(TAG, "Lokacija " + remoteMessage.getNotification().getClickAction());
        Log.d(TAG, "Value " + remoteMessage.getData().get("message"));
        sendNotification(remoteMessage);
        Log.d("Pozvana funkcija", "sendNotification");
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(myFirebaseMessagingService.this, MainActivity.class);
        intent.putExtra("click_action", "goToFragment1");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification=new NotificationCompat.Builder(this)
                .setSmallIcon(logo)
                .setContentText(remoteMessage.getData().get("message"))
                .setContentTitle("Naslov")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification.build());
    }
}
