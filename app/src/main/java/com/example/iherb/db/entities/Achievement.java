package com.example.iherb.db.entities;

import android.content.ContentValues;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.function.Predicate;

@DatabaseTable(tableName = "achievements")
public class Achievement {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String name;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String description;
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER)
    int score;

    public Achievement(String name, String description, int score) {

        this.name = name;
        this.description = description;
        this.score = score;
    }
}
