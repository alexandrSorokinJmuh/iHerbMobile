package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.EffectPill;
import com.example.iherb.db.entities.Pill;
import com.example.iherb.db.entities.UsePill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsePillDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public UsePillDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(UsePill.class);
    }

    public List<UsePill> getByParamId(String id) {
        String table = String.format("%s as usePill", tableName);
        String[] columns = {"usePill.param_value_id as param_value_id", "usePill.pill_id as pill_id"};
        String selection = "usePill.pill_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "pill_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<UsePill> usePillList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                UsePill usePill = new UsePill();
                usePill.setParam_value_id(c.getInt(c.getColumnIndex("param_value_id")));
                usePill.setPill_id(c.getInt(c.getColumnIndex("pill_id")));
                usePillList.add(usePill);

            }while (c.moveToNext());
        }
        c.close();
        return usePillList;
    }
    public long create(UsePill usePill) {
        ContentValues cv = new ContentValues();
        cv.put("param_value_id", usePill.getParam_value_id());
        cv.put("pill_id", usePill.getPill_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String param_value_id, String pill_id) {
        if (param_value_id.isEmpty() || pill_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "param_value_id = " + param_value_id + " and pill_id = " + pill_id,
                null);
    }
}