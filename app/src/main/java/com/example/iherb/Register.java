package com.example.iherb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iherb.db.DatabaseHelper;
import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.entities.User;

public class Register extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            HelperFactory.setHelper(this);
            DatabaseHelper databaseHelper = HelperFactory.getHelper();
        }

        public void registerButtonClick(View view) {

        }
}
