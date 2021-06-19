package com.example.iherb;

import android.app.NotificationManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.notification.NotificationCreator;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    NotificationCreator notificationCreator;
    View view;
    LinearLayout addWeightLayout;
    LinearLayout addPulseLayout;

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
        addWeightLayout = findViewById(R.id.addWeightLayout);
        addPulseLayout = findViewById(R.id.addPulseLayout);
    }

    public void showNotification(View view){
        notificationCreator.createNotification(this, "header", "description", 1);
    }

    public void showAddWeight(View view){
        addPulseLayout.setVisibility(View.GONE);
        addWeightLayout.setVisibility(View.VISIBLE);
    }
    public void showAddPulse(View view){
        addWeightLayout.setVisibility(View.GONE);
        addPulseLayout.setVisibility(View.VISIBLE);
    }

    public void showSnackBar(View view) throws SQLException {
        NotificationCreator.createSnackBar(view, HelperFactory.getHelper().getParamDao().getByName("weight").toString(), Snackbar.LENGTH_SHORT);
    }


}