package com.example.iherb.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "effect_pill")
public class EffectPill {
    @DatabaseField(foreign = true, id = true, uniqueCombo = true)
    Pill pill;
    @DatabaseField(foreign = true, id = true, uniqueCombo = true)
    Effect effect;


}
