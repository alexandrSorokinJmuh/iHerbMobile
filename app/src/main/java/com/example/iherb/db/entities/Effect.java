package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "effects")
public class Effect {
    @DatabaseField(generatedId = true)
    int Id;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String name;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String description;
    @DatabaseField(canBeNull = false, dataType = DataType.BOOLEAN)
    boolean isPositive;

    public Effect(String name, String description, boolean isPositive) {
        this.name = name;
        this.description = description;
        this.isPositive = isPositive;
    }
}
