package com.example.iherb.achievements;

import android.content.ContentValues;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.iherb.account.User;
import com.example.iherb.course.Course;

import java.util.function.Predicate;

public class Achievement {
    String name;
    String description;
    int score;
    Predicate<ContentValues> condition;


    public Achievement(String name, String description, int score, Predicate<ContentValues> condition) {
        this.name = name;
        this.description = description;
        this.score = score;
        this.condition = condition;
    }
}
