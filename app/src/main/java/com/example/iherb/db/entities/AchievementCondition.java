package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "achievement_conditions")
public class AchievementCondition {
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    Achievement achievement;
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    Param param;
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    ParamValue paramValue;

    public AchievementCondition(Achievement achievement, Param param, ParamValue paramValue) {
        this.achievement = achievement;
        this.param = param;
        this.paramValue = paramValue;
    }
}
