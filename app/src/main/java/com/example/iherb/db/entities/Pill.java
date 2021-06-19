package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pill extends Entity {
    @Column(type = "integer primary key autoincrement")
    private int Id;

    @Column
    String name;
    @Column
    String description;
    @Column(type = "integer", reference = "FOREIGN KEY (classification_id) REFERENCES classification(id)")
    Integer classification_id;

    public Pill(String name, String description, Integer classification_id) {
        this.name = name;
        this.description = description;
        this.classification_id = classification_id;
    }
}
