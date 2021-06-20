package com.example.iherb;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.dao.UserDao;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.User;
import com.example.iherb.db.entities.UserAchievement;
import com.example.iherb.notification.NotificationCreator;

import java.text.ParseException;
import java.util.Date;

public class Register extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText Date;
    EditText login;
    EditText password;
    EditText sex;
    EditText height;
    EditText weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextFirstName);
        login = findViewById(R.id.editTextFirstName);
        password = findViewById(R.id.editTextLastName);
        Date = findViewById(R.id.editTextDate);
        height = findViewById(R.id.editTextHeight);
        weight = findViewById(R.id.editTextWeight);
        sex = findViewById(R.id.editTextFirstName);

    }

    public void registerButtonClick(View view) throws ParseException {
        Date date = null;
        try {
            date = HelperFactory.getDateFromString(Date.getText().toString());
        } catch (ParseException e) {
            throw e;
        }

        User user = new User(firstName.getText().toString(), lastName.getText().toString(), (int)date.getTime(), login.getText().toString(), password.getText().toString(), sex.getText().toString());


        int userId = -1;
        try {
            userId = (int)HelperFactory.getHelper().getUserDao().create(user);

            Param weightParam = HelperFactory.getHelper().getParamDao().getByName("weight");
            Param heightParam = HelperFactory.getHelper().getParamDao().getByName("height");
            Param courseVisitedParam = HelperFactory.getHelper().getParamDao().getByName("courseVisited");
            Param pillDescriptionVisitedParam = HelperFactory.getHelper().getParamDao().getByName("pillDescriptionVisited");

            ParamValue weightParamValue = new ParamValue(weight.getText().toString());
            ParamValue heightParamValue = new ParamValue(height.getText().toString());
            ParamValue courseVisitedParamValue = new ParamValue("0");
            ParamValue pillDescriptionVisitedParamValue = new ParamValue("0");

            History historyWeight = new History(userId, weightParam.getId(), weightParamValue.getId(), (int)new Date().getTime());
            History historyHeight = new History(userId, heightParam.getId(), heightParamValue.getId(), (int)new Date().getTime());
            History courseVisited = new History(userId, courseVisitedParam.getId(), courseVisitedParamValue.getId(), (int)new Date().getTime());
            History pillDescriptionVisited = new History(userId, pillDescriptionVisitedParam.getId(), pillDescriptionVisitedParamValue.getId(), (int)new Date().getTime());

            HelperFactory.getHelper().getParamValueDao().create(weightParamValue);
            HelperFactory.getHelper().getParamValueDao().create(heightParamValue);
            HelperFactory.getHelper().getParamValueDao().create(courseVisitedParamValue);
            HelperFactory.getHelper().getParamValueDao().create(pillDescriptionVisitedParamValue);

            HelperFactory.getHelper().getHistoryDao().create(historyWeight);
            HelperFactory.getHelper().getHistoryDao().create(historyHeight);
            HelperFactory.getHelper().getHistoryDao().create(courseVisited);
            HelperFactory.getHelper().getHistoryDao().create(pillDescriptionVisited);

        } catch (Exception e) {

        }

        Intent intent = new Intent(this, MainActivity.class);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCreator notificationCreator = new NotificationCreator(notificationManager, "achievements", "Достижения",
                "Достижения приложения");
        Achievement achievement = HelperFactory.getHelper().getAchievementDao().getByName("Добро пожаловать");
        UserAchievement userAchievement = new UserAchievement(userId, achievement.getId(), 1);
        HelperFactory.getHelper().getUserAchievementDao().create(userAchievement);
        notificationCreator.createNotification(this, achievement.getName(), achievement.getDescription(), R.mipmap.ic_prop1_round,1);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
