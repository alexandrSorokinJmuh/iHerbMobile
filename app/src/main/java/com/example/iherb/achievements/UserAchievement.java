package com.example.iherb.achievements;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.iherb.account.User;
import com.example.iherb.notification.NotificationCreator;

import java.util.ArrayList;
import java.util.List;

public class UserAchievement { 
    User user;
    List<Achievement> achievementList = new ArrayList<>();
    List<Achievement> achievementSuccess = new ArrayList<>();
    NotificationCreator notificationCreator;

    public UserAchievement(NotificationCreator notificationCreator) {
        this.notificationCreator = notificationCreator;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void checkUserAchievement(Context context){
        ContentValues cv = new ContentValues();
        int achievementId = 1;
        for(Achievement achievement : achievementList){

            if(achievement.condition.test(cv)){
                achievementList.remove(achievement);
                notificationCreator.createNotification(context, achievement.name, achievement.description, ++achievementId);
                achievementSuccess.add(achievement);
            }
        }
    }
}
