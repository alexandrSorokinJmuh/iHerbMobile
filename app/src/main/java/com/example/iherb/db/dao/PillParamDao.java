package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.Pill;
import com.example.iherb.db.entities.PillParam;
import com.example.iherb.db.entities.UsePill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PillParamDao {
    SQLiteDatabase db;
    String tableName;

    public PillParamDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(PillParam.class);
    }

    public List<PillParam> getByParamId(String id) {
        String table = String.format("%s as pillParam", tableName);
        String[] columns = {"pillParam.param_id as param_id", "pillParam.pill_id as pill_id"};
        String selection = "pillParam.pill_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "pill_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<PillParam> pillParamList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                PillParam pillParam = new PillParam();
                pillParam.setParam_id(c.getInt(c.getColumnIndex("param_id")));
                pillParam.setPill_id(c.getInt(c.getColumnIndex("pill_id")));
                pillParamList.add(pillParam);

            }while (c.moveToNext());
        }
        c.close();
        return pillParamList;
    }

    public long create(PillParam pillParam) {
        ContentValues cv = new ContentValues();
        cv.put("param_id", pillParam.getParam_id());
        cv.put("pill_id", pillParam.getPill_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String param_id, String pill_id) {
        if (param_id.isEmpty() || pill_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "param_id = " + param_id + " and pill_id = " + pill_id,
                null);
    }
}
