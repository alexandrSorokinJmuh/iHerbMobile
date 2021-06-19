package com.example.iherb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFactory.setHelper(this);
        DatabaseHelper databaseHelper = HelperFactory.getHelper();
    }
}