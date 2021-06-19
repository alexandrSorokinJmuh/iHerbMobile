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
public class PillParam extends Entity{
    @Column(type = "integer", reference = "FOREIGN KEY (param_id) REFERENCES param(id)")
    Integer param_id;
    @Column(type = "integer", reference = "FOREIGN KEY (pill_id) REFERENCES pill(id), primary key (param_id, pill_id)")
    Integer pill_id;

    public PillParam(Integer param_id, Integer pill_id) {
        this.param_id = param_id;
        this.pill_id = pill_id;
    }
}
