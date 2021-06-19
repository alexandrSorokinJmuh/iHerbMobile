package com.example.iherb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iherb.db.dao.AchievementConditionDao;
import com.example.iherb.db.dao.AchievementDao;
import com.example.iherb.db.dao.ClassificationDao;
import com.example.iherb.db.dao.ContraindicationDao;
import com.example.iherb.db.dao.ContraindicationPillDao;
import com.example.iherb.db.dao.EffectDao;
import com.example.iherb.db.dao.EffectPillDao;
import com.example.iherb.db.dao.HistoryDao;
import com.example.iherb.db.dao.ParamDao;
import com.example.iherb.db.dao.ParamValueDao;
import com.example.iherb.db.dao.PillDao;
import com.example.iherb.db.dao.UsePillDao;
import com.example.iherb.db.dao.UserAchievementDao;
import com.example.iherb.db.dao.UserDao;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.AchievementCondition;
import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.ContraindicationPill;
import com.example.iherb.db.entities.Effect;
import com.example.iherb.db.entities.EffectPill;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.Pill;
import com.example.iherb.db.entities.UsePill;
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
    private AchievementConditionDao achievementConditionDao = null;
    private ClassificationDao classificationDao = null;
    private EffectDao effectDao = null;
    private HistoryDao historyDao = null;
    private ParamDao paramDao = null;
    private ParamValueDao paramValueDao = null;
    private PillDao pillDao = null;
    private UsePillDao usePillDao = null;
    private UserDao userDao = null;
    private AchievementDao achievementDao = null;
    private UserAchievementDao userAchievementDao = null;
    private ContraindicationDao contraindicationDao = null;
    private ContraindicationPillDao contraindicationPillDao = null;
    private EffectPillDao effectPillDao = null;

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
            TableUtils.createTable(connectionSource, Classification.class);
            TableUtils.createTable(connectionSource, Pill.class);
            TableUtils.createTable(connectionSource, Param.class);
            TableUtils.createTable(connectionSource, ParamValue.class);
            TableUtils.createTable(connectionSource, History.class);
            TableUtils.createTable(connectionSource, AchievementCondition.class);
            TableUtils.createTable(connectionSource, Effect.class);
            TableUtils.createTable(connectionSource, UsePill.class);
            TableUtils.createTable(connectionSource, Contraindication.class);
            TableUtils.createTable(connectionSource, ContraindicationPill.class);
            TableUtils.createTable(connectionSource, EffectPill.class);

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
            TableUtils.dropTable(connectionSource, Classification.class, true);
            TableUtils.dropTable(connectionSource, Pill.class, true);
            TableUtils.dropTable(connectionSource, Param.class, true);
            TableUtils.dropTable(connectionSource, ParamValue.class, true);
            TableUtils.dropTable(connectionSource, History.class, true);
            TableUtils.dropTable(connectionSource, AchievementCondition.class, true);
            TableUtils.dropTable(connectionSource, Effect.class, true);
            TableUtils.dropTable(connectionSource, UsePill.class, true);
            TableUtils.dropTable(connectionSource, Contraindication.class, true);
            TableUtils.dropTable(connectionSource, ContraindicationPill.class, true);
            TableUtils.dropTable(connectionSource, EffectPill.class, true);
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
    //синглтон для UserAchievementDao
    public UserAchievementDao getUserAchievementDao() throws SQLException{
        if(userAchievementDao == null){
            userAchievementDao = new UserAchievementDao(getConnectionSource(), UserAchievement.class);
        }
        return userAchievementDao;
    }

    //синглтон для AchievementConditionDao
    public AchievementConditionDao getAchievementConditionDao() throws SQLException{
        if(achievementConditionDao == null){
            achievementConditionDao = new AchievementConditionDao(getConnectionSource(), AchievementCondition.class);
        }
        return achievementConditionDao;
    }

    //синглтон для ClassificationDao
    public ClassificationDao getClassificationDao() throws SQLException{
        if(classificationDao == null){
            classificationDao = new ClassificationDao(getConnectionSource(), Classification.class);
        }
        return classificationDao;
    }

    //синглтон для EffectDao
    public EffectDao getEffectDao() throws SQLException{
        if(effectDao == null){
            effectDao = new EffectDao(getConnectionSource(), Effect.class);
        }
        return effectDao;
    }

    //синглтон для HistoryDao
    public HistoryDao getHistoryDao() throws SQLException{
        if(historyDao == null){
            historyDao = new HistoryDao(getConnectionSource(), History.class);
        }
        return historyDao;
    }

    //синглтон для ParamDao
    public ParamDao getParamDao() throws SQLException{
        if(paramDao == null){
            paramDao = new ParamDao(getConnectionSource(), Param.class);
        }
        return paramDao;
    }

    //синглтон для PillDao
    public PillDao getPillDao() throws SQLException{
        if(pillDao == null){
            pillDao = new PillDao(getConnectionSource(), Pill.class);
        }
        return pillDao;
    }

    //синглтон для UsePillDao
    public UsePillDao getUsePillDao() throws SQLException{
        if(usePillDao == null){
            usePillDao = new UsePillDao(getConnectionSource(), UsePill.class);
        }
        return usePillDao;
    }

    //синглтон для ContraindicationDao
    public ContraindicationDao getContraindicationDao() throws SQLException{
        if(contraindicationDao == null){
            contraindicationDao = new ContraindicationDao(getConnectionSource(), Contraindication.class);
        }
        return contraindicationDao;
    }

    //синглтон для ContraindicationPillDao
    public ContraindicationPillDao getContraindicationPillDao() throws SQLException{
        if(contraindicationPillDao == null){
            contraindicationPillDao = new ContraindicationPillDao(getConnectionSource(), ContraindicationPill.class);
        }
        return contraindicationPillDao;
    }

    //синглтон для effectPillDao
    public EffectPillDao getEffectPillDao() throws SQLException{
        if(effectPillDao == null){
            effectPillDao = new EffectPillDao(getConnectionSource(), EffectPill.class);
        }
        return effectPillDao;
    }
    //выполняется при закрытии приложения
    @Override
    public void close(){
        super.close();
        userDao = null;
        achievementDao = null;
        userAchievementDao = null;
        achievementConditionDao = null;
        classificationDao = null;
        effectDao = null;
        historyDao = null;
        paramDao = null;
        paramValueDao = null;
        pillDao = null;
        usePillDao = null;
        contraindicationDao = null;
        contraindicationPillDao = null;
        effectPillDao = null;
//        roleDao = null;
    }
}

