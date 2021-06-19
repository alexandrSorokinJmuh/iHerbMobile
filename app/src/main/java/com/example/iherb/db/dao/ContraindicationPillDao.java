package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.ContraindicationPill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContraindicationPillDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public ContraindicationPillDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(ContraindicationPill.class);
    }

    public List<ContraindicationPill> getByPillId(String id) {
        String table = String.format("%s as conPill", tableName);
        String[] columns = {"conPill.source_pill_id as source_pill_id", "conPill.affect_pill_id as affect_pill_id"};
        String selection = "conPill.source_pill_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "affect_pill_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<ContraindicationPill> contraindicationPillList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                ContraindicationPill contraindicationPill = new ContraindicationPill();
                contraindicationPill.setSource_pill_id(c.getInt(c.getColumnIndex("source_pill_id")));
                contraindicationPill.setAffect_pill_id(c.getInt(c.getColumnIndex("affect_pill_id")));
                contraindicationPillList.add(contraindicationPill);

            }while (c.moveToNext());
        }
        c.close();
        return contraindicationPillList;
    }
    public long create(ContraindicationPill contraindicationPill) {
        ContentValues cv = new ContentValues();
        cv.put("source_pill_id", contraindicationPill.getSource_pill_id());
        cv.put("affect_pill_id", contraindicationPill.getAffect_pill_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String source_pill_id, String affect_pill_id) {
        if (source_pill_id.isEmpty() || affect_pill_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "source_pill_id = " + source_pill_id + " and affect_pill_id = " + affect_pill_id,
                null);
    }
}