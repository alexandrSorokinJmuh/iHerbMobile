package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.AchievementCondition;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class AchievementDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public AchievementDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(Achievement.class);
    }

    public Achievement getById(String id) {
        String table = String.format("%s as ach", tableName);
        String[] columns = {"ach.id as id", "ach.name as name", "ach.description as description", "ach.score as score"};
        String selection = "ach.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        Achievement achievement = null;
        if (c.moveToFirst()) {
            achievement = new Achievement();
            achievement.setId(c.getInt(c.getColumnIndex("id")));
            achievement.setName(c.getString(c.getColumnIndex("name")));
            achievement.setDescription(c.getString(c.getColumnIndex("description")));
            achievement.setScore(c.getInt(c.getColumnIndex("score")));
        }
        c.close();
        return achievement;
    }

    public long create(Achievement achievement) {
        ContentValues cv = new ContentValues();
        cv.put("id", achievement.getId());
        cv.put("name", achievement.getName());
        cv.put("description", achievement.getDescription());
        cv.put("score", achievement.getScore());
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
