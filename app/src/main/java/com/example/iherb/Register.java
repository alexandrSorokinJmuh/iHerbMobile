package com.example.iherb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.dao.UserDao;
import com.example.iherb.db.entities.User;

import java.text.ParseException;

public class Register extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    CalendarView birthDateCalendar;
    EditText login;
    EditText password;
    EditText sex;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            HelperFactory.setHelper(this);
            DatabaseHelper databaseHelper = HelperFactory.getHelper();

            firstName = findViewById(R.id.editTextFirstName);
            lastName = findViewById(R.id.editTextLastName);
            birthDateCalendar = findViewById(R.id.calendarViewDateOFBirth);
            login = findViewById(R.id.editTextLoginRegister);
            password = findViewById(R.id.editTextPasswordRegister);
            sex = findViewById(R.id.editTextSex);

        }

        public void registerButtonClick(View view) {
            int date = (int)birthDateCalendar.getDate();

            try {
                HelperFactory.getHelper().getUserDao().create(new User(firstName.getText().toString(), lastName.getText().toString(), date, login.getText().toString(), password.getText().toString(), sex.getText().toString()));
            }catch (Exception e){

            }

        }
}
