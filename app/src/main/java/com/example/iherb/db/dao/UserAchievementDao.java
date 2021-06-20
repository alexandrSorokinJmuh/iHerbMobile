package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.UsePill;
import com.example.iherb.db.entities.User;
import com.example.iherb.db.entities.UserAchievement;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserAchievementDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public UserAchievementDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(UserAchievement.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<UserAchievement> getSuccessByUserId(String id){
        return getByUserId(id).stream()
                .filter(userAchievement -> userAchievement.getDateSuccess() != null)
                .collect(Collectors.toList());
    }
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<UserAchievement> getUnSuccessByUserId(String id){
        return getByUserId(id).stream()
                .filter(userAchievement -> userAchievement.getDateSuccess() == null)
                .collect(Collectors.toList());
    }

    public List<UserAchievement> getByUserId(String id) {
        String table = String.format("%s as uAch", tableName);
        String[] columns = {"uAch.user_id as user_id", "uAch.achievement_id as achievement_id", "uAch.dateSuccess as dateSuccess"};
        String selection = "uAch.user_id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "achievement_id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        List<UserAchievement> usePillList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                UserAchievement userAchievement = new UserAchievement();
                userAchievement.setUser_id(c.getInt(c.getColumnIndex("user_id")));
                userAchievement.setAchievement_id(c.getInt(c.getColumnIndex("achievement_id")));
                userAchievement.setDateSuccess(c.getInt(c.getColumnIndex("dateSuccess")));
                usePillList.add(userAchievement);

            }while (c.moveToNext());
        }
        c.close();
        return usePillList;
    }
    public long create(UserAchievement userAchievement) {
        ContentValues cv = new ContentValues();
        cv.put("user_id", userAchievement.getUser_id());
        cv.put("achievement_id", userAchievement.getAchievement_id());
        cv.put("dateSuccess", userAchievement.getDateSuccess());
        return db.insert(tableName, null, cv);
    }

    public int delete(String user_id, String achievement_id) {
        if (user_id.isEmpty() || achievement_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "user_id = " + user_id + " and achievement_id = " + achievement_id,
                null);
    }
}
