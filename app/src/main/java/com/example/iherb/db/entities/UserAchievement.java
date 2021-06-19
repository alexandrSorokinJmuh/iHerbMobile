package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "user_achievements")
public class UserAchievement { 
    @DatabaseField(foreign = true, id = true, uniqueCombo=true)
    User user;
    @DatabaseField(foreign = true, id = true, uniqueCombo=true)
    Achievement achievement;
    @DatabaseField(dataType = DataType.DATE_TIME)
    private Date dateSuccess;

    public UserAchievement(User user, Achievement achievement) {
        this.user = user;
        this.achievement = achievement;
    }
}
