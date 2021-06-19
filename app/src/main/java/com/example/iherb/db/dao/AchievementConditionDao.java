package com.example.iherb.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.AchievementCondition;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AchievementConditionDao implements Serializable {

    SQLiteDatabase db;
    String tableName;
    String achName;
    String paramName;
    String paramValueName;

    public AchievementConditionDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(AchievementCondition.class);
        achName = TableResolver.getTableName(Achievement.class);
        paramName = TableResolver.getTableName(Param.class);
        paramValueName = TableResolver.getTableName(ParamValue.class);
    }

    public long create(AchievementCondition achievementCondition) {
        ContentValues cv = new ContentValues();
        cv.put("achievement_id", achievementCondition.getAchievement_id());
        cv.put("param_id", achievementCondition.getParam_id());
        cv.put("param_value_id", achievementCondition.getParam_value_id());
        return db.insert(tableName, null, cv);
    }

    public int delete(String achievement_id, String param_id, String param_value_id) {
        if (achievement_id.isEmpty() || param_id.isEmpty() || param_value_id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName,  "achievement_id = " + achievement_id +
                                                " and param_id = " + param_id +
                                                " and param_value_id = " + param_value_id,
                null);
    }

    public List<AchievementCondition> getAchievementConditions(Integer achievement_id) {
        // делаем запрос всех данных из таблицы, получаем Cursor
        List<AchievementCondition> result = new ArrayList<>();
        if (achievement_id != null) {
            String table = String.format("%s as achCond", tableName);
            String[] columns = { "achCond.achievement_id as achId", "achCond.param_id as param", "achCond.param_value_id as paramValue" };
            String selection = "ach.id = ?";
            String[] selectionArgs = {achievement_id.toString()};
            Cursor c = db.query(table, columns, selection, selectionArgs, null, null, null);



            // ставим позицию курсора на первую строку выборки
            // если в выборке нет строк, вернется false
            if (c.moveToFirst()) {
                do {

                    AchievementCondition tag = new AchievementCondition();
                    tag.setAchievement_id(c.getInt(c.getColumnIndex("achId")));
                    tag.setParam_id(c.getInt(c.getColumnIndex("param")));
                    tag.setParam_value_id(c.getInt(c.getColumnIndex("paramValue")));
                    result.add(tag);
                } while (c.moveToNext());
            }
            c.close();
        }
        return result;
    }
}