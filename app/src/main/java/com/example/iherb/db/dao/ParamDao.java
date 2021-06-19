package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Effect;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ParamDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public ParamDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Param.class);
    }

    public Param getById(String id) {
        String table = String.format("%s as p", tableName);
        String[] columns = {"p.id as id", "p.name as name", "p.description as description"};
        String selection = "p.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Param param = null;
        if (c.moveToFirst()) {
            param = new Param();
            param.setId(c.getInt(c.getColumnIndex("id")));
            param.setName(c.getString(c.getColumnIndex("name")));
            param.setDescription(c.getString(c.getColumnIndex("description")));
        }
        c.close();
        return param;
    }

    public long create(Param param) {
        ContentValues cv = new ContentValues();
        cv.put("id", param.getId());
        cv.put("name", param.getName());
        cv.put("description", param.getDescription());
        return db.insert(tableName, null, cv);
    }

    public int delete(String id) {
        if (id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName, "id = " + id,
                null);
    }

    public Param getByName(String name){
        String table = String.format("%s as p", tableName);
        String[] columns = {"p.id as id", "p.name as name", "p.description as description"};
        String selection = "p.name = ?";
        String[] selectionArgs = {name};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Param param = null;
        if (c.moveToFirst()) {
            param = new Param();
            param.setId(c.getInt(c.getColumnIndex("id")));
            param.setName(c.getString(c.getColumnIndex("name")));
            param.setDescription(c.getString(c.getColumnIndex("description")));
        }
        c.close();
        return param;
    }
}