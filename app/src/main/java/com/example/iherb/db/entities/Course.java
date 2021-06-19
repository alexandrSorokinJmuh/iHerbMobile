package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.example.iherb.db.entities.Achievement;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course extends Entity {
    @Column(type = "integer primary key autoincrement")
    int Id;
    @Column
    String name;
    @Column
    String description;
    @Column(type = "integer")
    Integer days;
    @Column(type = "integer", reference = "FOREIGN KEY (param_id) REFERENCES param(id)")
    Integer param_id;

    public Course(String name, String description, Integer days, Integer param_id) {
        this.name = name;
        this.description = description;
        this.days = days;
        this.param_id = param_id;
    }
}
