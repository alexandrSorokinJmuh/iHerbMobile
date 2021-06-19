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
public class Param extends Entity {
    @Column(type = "integer primary key autoincrement")
    private int Id;

    @Column
    private String name;

    @Column
    private String description;

    public Param(String name, String description) {
        this.name = name;
        this.description = description;
    }

}

