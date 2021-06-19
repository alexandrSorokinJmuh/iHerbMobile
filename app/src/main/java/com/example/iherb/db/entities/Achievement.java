package com.example.iherb.db.entities;

import android.content.ContentValues;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.function.Predicate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Achievement extends Entity {
    @Column(type = "integer primary key autoincrement")
    private int Id;
    @Column
    String name;
    @Column
    String description;

    @Column
    Integer score;

    public Achievement(String name, String description, int score) {

        this.name = name;
        this.description = description;
        this.score = score;
    }
}
