package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.CoursePill;
import com.example.iherb.db.entities.CourseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseUserDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public CourseUserDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(CourseUser.class);
    }

    public List<CourseUser> getByUserId(String id) {
        String table = String.format("%s as cUser", tableName);
        String[] columns = {"cUser.user_id as user_id", "cUser.course_id as course_id", "cUser.date_begin as date_begin"};
        String selection = "cUser.user_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "pill_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<CourseUser> courseUserList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                CourseUser courseUser = new CourseUser();
                courseUser.setCourse_id(c.getInt(c.getColumnIndex("course_id")));
                courseUser.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                courseUser.setDate_begin(c.getInt(c.getColumnIndex("date_begin")));
                courseUserList.add(courseUser);

            }while (c.moveToNext());
        }
        c.close();
        return courseUserList;
    }
    public long create(CourseUser courseUser) {
        ContentValues cv = new ContentValues();
        cv.put("course_id", courseUser.getCourse_id());
        cv.put("user_id", courseUser.getUser_id());
        cv.put("date_begin", courseUser.getDate_begin());
        return db.insert(tableName, null, cv);
    }

    public int delete(String user_id, String course_id) {
        if (course_id.isEmpty() || user_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "user_id = " + user_id + " and course_id = " + course_id,
                null);
    }
}
