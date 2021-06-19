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
public class AchievementCondition extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (achievement_id) REFERENCES achievement(id)")
    Integer achievement_id;
    @Column(type = "integer", reference = "FOREIGN KEY (param_id) REFERENCES param(id)")
    Integer param_id;
    @Column(type = "integer", reference = "FOREIGN KEY (param_value_id) REFERENCES param_value(id), primary key (achievement_id, param_id, param_value_id)")
    Integer param_value_id;

    public AchievementCondition(Integer achievement_id, Integer param_id, Integer param_value_id) {
        this.achievement_id = achievement_id;
        this.param_id = param_id;
        this.param_value_id = param_value_id;
    }
}
