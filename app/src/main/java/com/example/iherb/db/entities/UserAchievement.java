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
public class UserAchievement extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (user_id) REFERENCES user(id)")
    Integer user_id;
    @Column(type = "integer", reference = "FOREIGN KEY (achievement_id) REFERENCES achievement(id), primary key (user_id, achievement_id)")
    Integer achievement_id;
    @Column
    private Integer dateSuccess;

    public UserAchievement(Integer user_id, Integer achievement_id, Integer dateSuccess) {
        this.user_id = user_id;
        this.achievement_id = achievement_id;
        this.dateSuccess = dateSuccess;
    }
}
