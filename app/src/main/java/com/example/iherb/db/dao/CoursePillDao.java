package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Course;
import com.example.iherb.db.entities.CoursePill;
import com.example.iherb.db.entities.EffectPill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoursePillDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public CoursePillDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(CoursePill.class);
    }

    public List<CoursePill> getByCourseId(String id) {
        String table = String.format("%s as cPill", tableName);
        String[] columns = {"cPill.course_id as course_id", "cPill.pill_id as pill_id", "cPill.description as description"};
        String selection = "cPill.course_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "pill_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<CoursePill> coursePillList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                CoursePill coursePill = new CoursePill();
                coursePill.setCourse_id(c.getInt(c.getColumnIndex("course_id")));
                coursePill.setPill_id(c.getInt(c.getColumnIndex("pill_id")));
                coursePill.setDescription(c.getString(c.getColumnIndex("description")));
                coursePillList.add(coursePill);

            }while (c.moveToNext());
        }
        c.close();
        return coursePillList;
    }
    public long create(CoursePill coursePill) {
        ContentValues cv = new ContentValues();
        cv.put("course_id", coursePill.getCourse_id());
        cv.put("pill_id", coursePill.getPill_id());
        cv.put("description", coursePill.getDescription());
        return db.insert(tableName, null, cv);
    }

    public int delete(String course_id, String pill_id) {
        if (course_id.isEmpty() || pill_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "course_id = " + course_id + " and pill_id = " + pill_id,
                null);
    }
}
