package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.UserAchievement;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ClassificationDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public ClassificationDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Classification.class);
    }

    public Classification getById(String id) {
        String table = String.format("%s as cl", tableName);
        String[] columns = {"cl.id as id", "cl.name as name", "cl.description as description"};
        String selection = "cl.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Classification classification = null;
        if (c.moveToFirst()) {
            classification = new Classification();
            classification.setId(c.getInt(c.getColumnIndex("id")));
            classification.setName(c.getString(c.getColumnIndex("name")));
            classification.setDescription(c.getString(c.getColumnIndex("description")));
        }
        c.close();
        return classification;
    }
    public long create(Classification classification) {
        ContentValues cv = new ContentValues();
        cv.put("id", classification.getId());
        cv.put("name", classification.getName());
        cv.put("description", classification.getDescription());
        return db.insert(tableName, null, cv);
    }

    public int delete(String id) {
        if (id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "id = " + id ,
                null);
    }
}