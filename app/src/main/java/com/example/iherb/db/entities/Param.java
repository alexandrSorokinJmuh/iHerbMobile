package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "params")
public class Param {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String description;

    public Param(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

