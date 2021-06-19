package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EffectPill extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (pill_id) REFERENCES pill(id)")
    Integer pill_id;
    @Column(type = "integer", reference = "FOREIGN KEY (effect_id) REFERENCES effect(id), primary key (pill_id, effect_id)")
    Integer effect_id;

    public EffectPill(Integer pill_id, Integer effect_id) {
        this.pill_id = pill_id;
        this.effect_id = effect_id;
    }
}
