package com.example.iherb.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.dao.AchievementConditionDao;
import com.example.iherb.db.dao.AchievementDao;
import com.example.iherb.db.dao.ClassificationDao;
import com.example.iherb.db.dao.ContraindicationDao;
import com.example.iherb.db.dao.ContraindicationPillDao;
import com.example.iherb.db.dao.CourseDao;
import com.example.iherb.db.dao.CoursePillDao;
import com.example.iherb.db.dao.CourseUserDao;
import com.example.iherb.db.dao.EffectDao;
import com.example.iherb.db.dao.EffectPillDao;
import com.example.iherb.db.dao.HistoryDao;
import com.example.iherb.db.dao.ParamDao;
import com.example.iherb.db.dao.ParamValueDao;
import com.example.iherb.db.dao.PillDao;
import com.example.iherb.db.dao.UsePillDao;
import com.example.iherb.db.dao.UserAchievementDao;
import com.example.iherb.db.dao.UserDao;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperFactory{

    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getHelper(){
        return databaseHelper;
    }
    public static void setHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        databaseHelper.setAchievementDao(new AchievementDao(db));
        databaseHelper.setAchievementConditionDao(new AchievementConditionDao(db));
        databaseHelper.setClassificationDao(new ClassificationDao(db));
        databaseHelper.setContraindicationDao(new ContraindicationDao(db));
        databaseHelper.setContraindicationPillDao(new ContraindicationPillDao(db));
        databaseHelper.setEffectDao(new EffectDao(db));
        databaseHelper.setEffectPillDao(new EffectPillDao(db));
        databaseHelper.setHistoryDao(new HistoryDao(db));
        databaseHelper.setParamDao(new ParamDao(db));
        databaseHelper.setParamValueDao(new ParamValueDao(db));
        databaseHelper.setPillDao(new PillDao(db));
        databaseHelper.setUsePillDao(new UsePillDao(db));
        databaseHelper.setUserAchievementDao(new UserAchievementDao(db));
        databaseHelper.setUserDao(new UserDao(db));
        databaseHelper.setCourseDao(new CourseDao(db));
        databaseHelper.setCoursePillDao(new CoursePillDao(db));
        databaseHelper.setCourseUserDao(new CourseUserDao(db));
        databaseHelper.addStartValues();
    }
    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }


    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String getDateFormat(Long date){
        return simpleDateFormat.format(new Date(date));
    }

    public static Date getDateFromString(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }
}
