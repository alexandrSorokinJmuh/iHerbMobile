package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.Effect;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class EffectDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public EffectDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Effect.class);
    }

    public Effect getById(String id) {
        String table = String.format("%s as ef", tableName);
        String[] columns = {"ef.id as id", "ef.name as name", "ef.description as description", "ef.positive as positive"};
        String selection = "ef.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Effect effect = null;
        if (c.moveToFirst()) {
            effect = new Effect();
            effect.setId(c.getInt(c.getColumnIndex("id")));
            effect.setName(c.getString(c.getColumnIndex("name")));
            effect.setDescription(c.getString(c.getColumnIndex("description")));
            effect.setPositive(c.getString(c.getColumnIndex("positive")));
        }
        c.close();
        return effect;
    }

    public long create(Effect effect) {
        ContentValues cv = new ContentValues();
        cv.put("id", effect.getId());
        cv.put("name", effect.getName());
        cv.put("description", effect.getDescription());
        cv.put("positive", effect.getPositive());
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