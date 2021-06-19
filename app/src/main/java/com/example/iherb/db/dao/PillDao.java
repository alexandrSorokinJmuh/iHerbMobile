package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.Pill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class PillDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public PillDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Pill.class);
    }

    public Pill getById(String id) {
        String table = String.format("%s as pill", tableName);
        String[] columns = {"pill.id as id", "pill.name as name", "pill.description as description", "pill.classification_id as classification_id"};
        String selection = "pill.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Pill pill = null;
        if (c.moveToFirst()) {
            pill = new Pill();
            pill.setId(c.getInt(c.getColumnIndex("id")));
            pill.setName(c.getString(c.getColumnIndex("name")));
            pill.setDescription(c.getString(c.getColumnIndex("description")));
            pill.setClassification_id(c.getInt(c.getColumnIndex("classification_id")));
        }
        c.close();
        return pill;
    }

    public long create(Pill pill) {
        ContentValues cv = new ContentValues();
        cv.put("id", pill.getId());
        cv.put("name", pill.getName());
        cv.put("description", pill.getDescription());
        cv.put("classification_id", pill.getClassification_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String id) {
        if (id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName, "id = " + id,
                null);
    }
}