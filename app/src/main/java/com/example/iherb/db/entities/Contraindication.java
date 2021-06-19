package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Contraindication extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (param_id) REFERENCES param(id)")
    Integer param_id;

    @Column(type = "integer", reference = "FOREIGN KEY (effect_id) REFERENCES effect(id), primary key (param_id, effect_id)")
    Integer effect_id;

    public Contraindication(Integer param_id, Integer effect_id) {
        this.param_id = param_id;
        this.effect_id = effect_id;
    }
}
