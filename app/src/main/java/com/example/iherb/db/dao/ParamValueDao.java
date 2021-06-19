package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ParamValueDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public ParamValueDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(ParamValue.class);
    }

    public ParamValue getById(String id) {
        String table = String.format("%s as pv", tableName);
        String[] columns = {"pv.id as id", "pv.value as value"};
        String selection = "pv.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        ParamValue paramValue = null;
        if (c.moveToFirst()) {
            paramValue = new ParamValue();
            paramValue.setId(c.getInt(c.getColumnIndex("id")));
            paramValue.setValue(c.getString(c.getColumnIndex("name")));
        }
        c.close();
        return paramValue;
    }

    public long create(ParamValue paramValue) {
        ContentValues cv = new ContentValues();
        cv.put("id", paramValue.getId());
        cv.put("value", paramValue.getValue());
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