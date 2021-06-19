package com.example.iherb;

import android.app.NotificationManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.notification.NotificationCreator;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    NotificationCreator notificationCreator;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationCreator =
                new NotificationCreator(notificationManager, "test_channel_id", "channel_name", "channel_description");

    }

    public void showNotification(View view){
        notificationCreator.createNotification(this, "header", "description", 1);
    }

    public void showSnackBar(View view) throws SQLException {
        NotificationCreator.createSnackBar(view, HelperFactory.getHelper().getParamDao().getByName("weight").toString(), Snackbar.LENGTH_SHORT);
    }


}