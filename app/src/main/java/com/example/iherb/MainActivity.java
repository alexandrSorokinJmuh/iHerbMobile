package com.example.iherb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.entities.User;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
    }

    public void registerButtonClick(View view) {
        EditText firtName = (EditText)findViewById(R.id.editTextFirstName);
        EditText lastName = (EditText)findViewById(R.id.editTextLastName);
        DatePicker dateOfBirth = (DatePicker)findViewById(R.id.dateBirthDate);
        EditText login = (EditText)findViewById(R.id.editTextLogin);
        EditText password = (EditText)findViewById(R.id.editTextPassword);
        EditText sex = (EditText)findViewById(R.id.editTextSex);

        int day = dateOfBirth.getDayOfMonth();
        int month = dateOfBirth.getMonth();
        int year = dateOfBirth.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        User user = new User(firtName.getText().toString(), lastName.getText().toString(), calendar.getTime(), login.getText().toString(), password.getText().toString(), sex.getText().toString());

        try {
            HelperFactory.getHelper().getUserDao().create(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}