package com.example.iherb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.entities.User;

import java.sql.SQLException;
import java.util.List;

public class Signin extends AppCompatActivity {

        User user;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            HelperFactory.setHelper(this);
            DatabaseHelper databaseHelper = HelperFactory.getHelper();
        }

        /*public void signinButtonClick(View view) {
            EditText login = (EditText)findViewById(R.id.editTextLoginSignin);
            EditText password = (EditText)findViewById(R.id.editTextPasswordSignin);

            User loginingUser;
            boolean isUserExists = false;
            try {
                isUserExists = HelperFactory.getHelper().getUserDao().checkUserExisting(login.getText().toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(isUserExists) {
                try {
                   user = HelperFactory.getHelper().getUserDao().getUserByLoginAndPassword(login.getText().toString(), password.getText().toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

                else return;

        }*/
}
