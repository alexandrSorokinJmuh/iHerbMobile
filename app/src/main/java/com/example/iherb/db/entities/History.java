package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class History extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (user_id) REFERENCES user(id)")
    Integer user_id;
    @Column(type = "integer", reference = "FOREIGN KEY (param_id) REFERENCES param(id)")
    Integer param_id;
    @Column(type = "integer", reference = "FOREIGN KEY (param_value_id) REFERENCES param_value(id), primary key (user_id, param_id, param_value_id)")
    Integer param_value_id;
    @Column(type = "integer")
    Integer date;

    public History(Integer user_id, Integer param_id, Integer param_value_id, Integer date) {
        this.user_id = user_id;
        this.param_id = param_id;
        this.param_value_id = param_value_id;
        this.date = date;
    }
}
