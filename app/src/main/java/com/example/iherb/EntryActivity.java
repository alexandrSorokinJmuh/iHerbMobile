package com.example.iherb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
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