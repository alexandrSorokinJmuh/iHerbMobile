package com.example.iherb.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contraindication_pill")
public class ContraindicationPill {
    @DatabaseField(foreign = true, uniqueCombo = true, id = true)
    Pill sourcePill;
    @DatabaseField(foreign = true, uniqueCombo = true, id = true)
    Pill affectPill;

}
