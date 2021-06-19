package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.Effect;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContraindicationDao implements Serializable {


    SQLiteDatabase db;
    String tableName;

    public ContraindicationDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Contraindication.class);
    }
    public List<Contraindication> getByParamId(String id) {
        String table = String.format("%s as con", tableName);
        String[] columns = {"con.param_id as param_id", "con.effect_id as effect_id"};
        String selection = "con.param_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "effect_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<Contraindication> contraindicationList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Contraindication contraindication = new Contraindication();
                contraindication.setParam_id(c.getInt(c.getColumnIndex("param_id")));
                contraindication.setEffect_id(c.getInt(c.getColumnIndex("effect_id")));
                contraindicationList.add(contraindication);

            }while (c.moveToNext());
        }
        c.close();
        return contraindicationList;
    }
    public long create(Contraindication contraindication) {
        ContentValues cv = new ContentValues();
        cv.put("param_id", contraindication.getParam_id());
        cv.put("effect_id", contraindication.getEffect_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String param_id, String effect_id) {
        if (param_id.isEmpty() || effect_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "param_id = " + param_id + " and effect_id = " + effect_id,
                null);
    }
}