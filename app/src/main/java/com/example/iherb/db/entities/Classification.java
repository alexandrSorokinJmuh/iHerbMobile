package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "classifications")
public class Classification {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String name;
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    String description;

    public Classification(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
