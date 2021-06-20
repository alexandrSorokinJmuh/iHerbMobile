package com.example.iherb.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.example.iherb.R;

public class NotificationCreator {
    int MAX_CHAR_LENGTH = 20;
    String CHANNEL_ID;
    NotificationManager notificationManager;

    public NotificationCreator(NotificationManager notificationManager, String CHANNEL_ID, String channelName, String channelDescription) {
        this.CHANNEL_ID = CHANNEL_ID;
        this.notificationManager = notificationManager;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(channelDescription);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotification(Context context, String header, String description, int ic_resource, int notificationId){

//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle(header);
//        if (description.length() > MAX_CHAR_LENGTH) {
//            notificationBuilder.setContentText(description.substring(0, MAX_CHAR_LENGTH) + "...");
//            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(description));
//        }else {
//            notificationBuilder.setContentText(description);
//        }
//        notificationBuilder
//                .setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),
//                        R.drawable.ic_launcher_foreground))
//                .setAutoCancel(true)
//                .setColor(Color.GREEN)
//                .setBadgeIconType(R.drawable.ic_launcher_foreground);
//
//        Notification notification = notificationBuilder.build();
//        notificationManager.notify(notificationId, notification);

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.custom_push);
        contentView.setImageViewResource(R.id.image, ic_resource);
        contentView.setTextViewText(R.id.title, header);
        contentView.setTextViewText(R.id.text, description);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(ic_resource)
                .setContent(contentView);



        Notification notification = mBuilder.build();
        notification.bigContentView = contentView;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(notificationId, notification);

    }

    public static void createSnackBar(View view, String text, int duration){
        Snackbar snackbar = Snackbar.make(view, text, duration);
        snackbar.show();
    }
}
