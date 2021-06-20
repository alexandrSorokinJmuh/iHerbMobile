package com.example.iherb;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.User;
import com.example.iherb.notification.NotificationCreator;

import java.sql.SQLException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    NotificationCreator notificationCreator;
    View view;
    LinearLayout addWeightLayout;
    LinearLayout addPulseLayout;
    EditText weightEditText;
    EditText pulseEditText;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationCreator = new NotificationCreator(notificationManager, "achievements", "Достижения",
                "Достижения приложения");
        addWeightLayout = findViewById(R.id.addWeightLayout);
        addPulseLayout = findViewById(R.id.addPulseLayout);
        weightEditText = findViewById(R.id.weightNumber);
        pulseEditText = findViewById(R.id.pulseNumber);
        user = new User("алёша", "помоги", 233123, "asd", "asd", "м");
        user = (User)this.getIntent().getSerializableExtra("user");

    }

    public void showNotification(View view){
        notificationCreator.createNotification(this, "header", "description", R.mipmap.ic_prop1_round,1);
    }

    public void showAddWeight(View view){
        addPulseLayout.setVisibility(View.GONE);
        addWeightLayout.setVisibility(View.VISIBLE);
    }
    public void showAddPulse(View view){
        addWeightLayout.setVisibility(View.GONE);
        addPulseLayout.setVisibility(View.VISIBLE);
    }

    public void addWeight(View view){
        String weight = weightEditText.getText().toString();
        int weightId = HelperFactory.getHelper().getParamDao().getByName("weight").getId();
        long pvId = HelperFactory.getHelper().getParamValueDao().create(new ParamValue(weight));
        History history = new History(user.getId(), weightId, (int)pvId, (int)Calendar.getInstance().getTime().getTime());
        HelperFactory.getHelper().getHistoryDao().create(history);

    }
    public void addPulse(View view){
        String pulse = pulseEditText.getText().toString();
        int pulseId = HelperFactory.getHelper().getParamDao().getByName("pulse").getId();
        long pvId = HelperFactory.getHelper().getParamValueDao().create(new ParamValue(pulse));
        History history = new History(user.getId(), pulseId, (int)pvId, (int)Calendar.getInstance().getTime().getTime());
        HelperFactory.getHelper().getHistoryDao().create(history);

    }

    public void goToAchievements(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    public void showSnackBar(View view) throws SQLException {
        NotificationCreator.createSnackBar(view, HelperFactory.getHelper().getParamDao().getByName("weight").toString(), Snackbar.LENGTH_SHORT);
    }


}