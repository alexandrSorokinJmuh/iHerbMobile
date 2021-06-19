package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pill")
public class Pill {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String name;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String description;
    @DatabaseField(foreign = true)
    Classification classification;
}
