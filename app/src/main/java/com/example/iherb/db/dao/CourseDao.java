package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Course;
import com.example.iherb.db.entities.Effect;

import java.io.Serializable;

public class CourseDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public CourseDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Course.class);
    }

    public Course getById(String id) {
        String table = String.format("%s as c", tableName);
        String[] columns = {"c.id as id", "c.name as name", "c.description as description", "c.days as days"};
        String selection = "c.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Course course = null;
        if (c.moveToFirst()) {
            course = new Course();
            course.setId(c.getInt(c.getColumnIndex("id")));
            course.setName(c.getString(c.getColumnIndex("name")));
            course.setDescription(c.getString(c.getColumnIndex("description")));
            course.setDays(c.getInt(c.getColumnIndex("days")));

        }
        c.close();
        return course;
    }

    public long create(Course course) {
        ContentValues cv = new ContentValues();
        cv.put("id", course.getId());
        cv.put("name", course.getName());
        cv.put("description", course.getDescription());
        cv.put("days", course.getDays());
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
