package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.ContraindicationPill;
import com.example.iherb.db.entities.EffectPill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EffectPillDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public EffectPillDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(EffectPill.class);
    }

    public List<EffectPill> getByPillId(String id) {
        String table = String.format("%s as effPill", tableName);
        String[] columns = {"effPill.pill_id as pill_id", "effPill.effect_id as effect_id"};
        String selection = "effPill.pill_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "effect_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<EffectPill> effectPillList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                EffectPill effectPill = new EffectPill();
                effectPill.setPill_id(c.getInt(c.getColumnIndex("pill_id")));
                effectPill.setEffect_id(c.getInt(c.getColumnIndex("effect_id")));
                effectPillList.add(effectPill);

            }while (c.moveToNext());
        }
        c.close();
        return effectPillList;
    }
    public long create(EffectPill effectPill) {
        ContentValues cv = new ContentValues();
        cv.put("pill_id", effectPill.getPill_id());
        cv.put("effect_id", effectPill.getEffect_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String pill_id, String effect_id) {
        if (pill_id.isEmpty() || effect_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "pill_id = " + pill_id + " and effect_id = " + effect_id,
                null);
    }
}