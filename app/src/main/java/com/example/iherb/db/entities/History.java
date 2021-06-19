package com.example.iherb.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "histories")
public class History {
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    User user;
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    Param param;
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    ParamValue paramValue;

    public History(User user, Param param, ParamValue paramValue) {
        this.user = user;
        this.param = param;
        this.paramValue = paramValue;
    }
}
