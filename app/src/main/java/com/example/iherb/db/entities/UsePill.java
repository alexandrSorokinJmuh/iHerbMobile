package com.example.iherb.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "use_pill")
public class UsePill {
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    ParamValue paramValue;
    @DatabaseField(id = true, foreign = true, uniqueCombo = true)
    Pill pill;

}
