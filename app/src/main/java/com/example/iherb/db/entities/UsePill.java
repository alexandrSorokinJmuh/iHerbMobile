package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsePill extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (param_value_id) REFERENCES param_value(id)")
    Integer param_value_id;
    @Column(type = "integer", reference = "FOREIGN KEY (pill_id) REFERENCES pill(id), primary key (param_value_id, pill_id)")
    Integer pill_id;

    public UsePill(Integer param_value_id, Integer pill_id) {
        this.param_value_id = param_value_id;
        this.pill_id = pill_id;
    }
}
