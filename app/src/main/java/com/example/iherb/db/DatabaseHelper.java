package com.example.iherb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iherb.db.dao.AchievementDao;
import com.example.iherb.db.dao.UserAchievementDao;
import com.example.iherb.db.dao.UserDao;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.User;
import com.example.iherb.db.entities.UserAchievement;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
    private static final String DATABASE_NAME ="iHerb.db";

    //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
    private static final int DATABASE_VERSION = 1;

    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private UserDao userDao = null;
    private AchievementDao achievementDao = null;
    private UserAchievementDao userAchievementDao = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Выполняется, когда файл с БД не найден на устройстве
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Achievement.class);
            TableUtils.createTable(connectionSource, UserAchievement.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    //Выполняется, когда БД имеет версию отличную от текущей
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){
        try{
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Achievement.class, true);
            TableUtils.dropTable(connectionSource, UserAchievement.class, true);

            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);
            throw new RuntimeException(e);
        }
    }

    //синглтон для UserDao
    public UserDao getUserDao() throws SQLException{
        if(userDao == null){
            userDao = new UserDao(getConnectionSource(), User.class);
        }
        return userDao;
    }

    //синглтон для AchievementDao
    public AchievementDao getAchievementDao() throws SQLException{
        if(achievementDao == null){
            achievementDao = new AchievementDao(getConnectionSource(), Achievement.class);
        }
        return achievementDao;
    }
    //синглтон для GoalDAO
    public UserAchievementDao getUserAchievementDao() throws SQLException{
        if(userAchievementDao == null){
            userAchievementDao = new UserAchievementDao(getConnectionSource(), UserAchievement.class);
        }
        return userAchievementDao;
    }
    //выполняется при закрытии приложения
    @Override
    public void close(){
        super.close();
        userDao = null;
        achievementDao = null;
        userAchievementDao = null;
//        roleDao = null;
    }
}

