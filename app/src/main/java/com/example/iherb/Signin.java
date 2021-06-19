package com.example.iherb;

import android.content.Intent;
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
        setContentView(R.layout.sign_in_layout);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
    }

    public void signinButtonClick(View view) {
        EditText login = (EditText) findViewById(R.id.editTextLoginSignin);
        EditText password = (EditText) findViewById(R.id.editTextPasswordSignin);

        User loginingUser = null;
        try {
            loginingUser = HelperFactory.getHelper().getUserDao().getUserByLogin(login.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginingUser != null) {
            try {
                user = HelperFactory.getHelper().getUserDao().getUserByLoginAndPass(login.getText().toString(), password.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            user = loginingUser;
        } else return;


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
