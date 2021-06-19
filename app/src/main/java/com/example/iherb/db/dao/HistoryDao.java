package com.example.iherb.db.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.EffectPill;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public HistoryDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(History.class);
    }

    public List<History> getByUserId(String id) {
        String table = String.format("%s as h", tableName);
        String[] columns = {"h.user_id as user_id", "h.param_id as param_id", "h.param_value_id as param_value_id", " h.date as date"};
        String selection = "h.user_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "param_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<History> historyList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                History history = new History();
                history.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                history.setParam_id(c.getInt(c.getColumnIndex("param_id")));
                history.setParam_value_id(c.getInt(c.getColumnIndex("param_value_id")));
                history.setDate(c.getInt(c.getColumnIndex("date")));
                historyList.add(history);

            }while (c.moveToNext());
        }
        c.close();
        return historyList;
    }
    public long create(History history) {
        ContentValues cv = new ContentValues();
        cv.put("user_id", history.getUser_id());
        cv.put("param_id", history.getParam_id());
        cv.put("param_value_id", history.getParam_value_id());
        cv.put("date", history.getDate());
        return db.insert(tableName, null, cv);
    }

    public int delete(String user_id, String param_id, String param_value_id) {
        if (user_id.isEmpty() || param_id.isEmpty() || param_value_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "user_id = " + user_id + " and param_id = " + param_id + " and param_value_id = " + param_value_id,
                null);
    }
}