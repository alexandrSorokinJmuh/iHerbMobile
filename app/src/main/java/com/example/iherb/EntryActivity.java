package com.example.iherb;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.notification.NotificationCreator;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCreator notificationCreator = new NotificationCreator(notificationManager, "achievements", "Достижения",
                "Достижения приложения");
        Achievement achievement = HelperFactory.getHelper().getAchievementDao().getByName("Добро пожаловать");
        notificationCreator.createNotification(this, achievement.getName(), achievement.getDescription(), 1);
    }

    public void buttonEnterRegistryClick(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void buttonEnterSigninClick(View view) {
        Intent intent = new Intent(this, Signin.class);
        startActivity(intent);
    }
}