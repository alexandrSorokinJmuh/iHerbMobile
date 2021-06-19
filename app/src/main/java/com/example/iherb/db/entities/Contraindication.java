package com.example.iherb.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contraindications")
public class Contraindication {
    @DatabaseField(foreign = true, id = true, uniqueCombo = true)
    Param param;
    @DatabaseField(foreign = true, id = true, uniqueCombo = true)
    Effect effect;

    public Contraindication(Param param, Effect effect) {
        this.param = param;
        this.effect = effect;
    }
}
