package com.example.iherb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

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
import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.AchievementCondition;
import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.ContraindicationPill;
import com.example.iherb.db.entities.Course;
import com.example.iherb.db.entities.CoursePill;
import com.example.iherb.db.entities.CourseUser;
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
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    //?????? ?????????? ???????? ???????????? ?????????????? ?????????? ?????????????????? ?? /data/data/APPNAME/DATABASE_NAME.db
    private static final String DATABASE_NAME ="iHerb.db";

    //?? ???????????? ?????????????????????? ????????????, ?????? ???????????????????? ?? ???????????????????? ???? ?? ???????????????????? ?????????????? ?????????? ???????????????? ?????????? onUpgrade();
    private static final int DATABASE_VERSION = 1;

    //???????????? ???? DAO ???????????????????????????? ??????????????????, ???????????????? ?? ????
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
    private CourseDao courseDao = null;
    private CoursePillDao coursePillDao = null;
    private CourseUserDao courseUserDao = null;

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public CoursePillDao getCoursePillDao() {
        return coursePillDao;
    }

    public void setCoursePillDao(CoursePillDao coursePillDao) {
        this.coursePillDao = coursePillDao;
    }

    public CourseUserDao getCourseUserDao() {
        return courseUserDao;
    }

    public void setCourseUserDao(CourseUserDao courseUserDao) {
        this.courseUserDao = courseUserDao;
    }

    private SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableResolver.getSqlCreateTableString(Achievement.class));
        db.execSQL(TableResolver.getSqlCreateTableString(AchievementCondition.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Classification.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Contraindication.class));
        db.execSQL(TableResolver.getSqlCreateTableString(ContraindicationPill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Effect.class));
        db.execSQL(TableResolver.getSqlCreateTableString(EffectPill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(History.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Param.class));
        db.execSQL(TableResolver.getSqlCreateTableString(ParamValue.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Pill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(UsePill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(User.class));
        db.execSQL(TableResolver.getSqlCreateTableString(UserAchievement.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Course.class));
        db.execSQL(TableResolver.getSqlCreateTableString(CoursePill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(CourseUser.class));

    }

    public void addStartValues(){
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param("weight", "user weight"));
        paramList.add(new Param("pulse", "user pulse"));
        paramList.add(new Param("height", "user height"));
        paramList.add(new Param("courseVisited", "course visited"));
        paramList.add(new Param("pillDescriptionVisited", "pill description visited"));
        paramList.add(new Param("userScore", "user score"));

        List<Achievement> achievementList = new ArrayList<>();
        achievementList.add(new Achievement("?????????? ????????????????????", "???? ?????????????? ???????????????????????????????????? ?? ????????????????????", 5));


        List<Classification> classificationList = new ArrayList<>();
        classificationList.add(new Classification("????????????????????????", "?????????????????????????????? ????????????????, ?????????? ?????????????? ?????????????? ?????? ???????????????? ???????????????? ?????????????? ?? ???????????????????????????????? ???? ?????????? ?? ???????????? ??????????????????, ?? ?????????? ?????? ?????????????????? ?????????????????? ???????????????????? ???????????????????????? ?? ???????????????????????????? ????????????????????"));
        classificationList.add(new Classification("????????????????????????????????", "?????????????????????? ??????????????, ???????????????????????? ?????? ?????????????????? ???????????????????? ?? ???????????????????? ??????????????????????????????????, ???????????????????? ????????????????????"));
        classificationList.add(new Classification("??????????????????", "??????, ???????????????????? ?????????? ???????????????? ???????????????? ?? (??????) ?????????????????????? ???????????????? ?????? ??????, ?????????????????????? ?????? ???????????????????????????? ???????????????????????????? ?????????????????? ?? ????."));

        for (Achievement achievement : achievementList)
            getAchievementDao().create(achievement);
        for (Param param : paramList)
            getParamDao().create(param);

        for (Classification classification : classificationList){
            getClassificationDao().create(classification);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public AchievementConditionDao getAchievementConditionDao() {
        return achievementConditionDao;
    }

    public void setAchievementConditionDao(AchievementConditionDao achievementConditionDao) {
        this.achievementConditionDao = achievementConditionDao;
    }

    public ClassificationDao getClassificationDao() {
        return classificationDao;
    }

    public void setClassificationDao(ClassificationDao classificationDao) {
        this.classificationDao = classificationDao;
    }

    public EffectDao getEffectDao() {
        return effectDao;
    }

    public void setEffectDao(EffectDao effectDao) {
        this.effectDao = effectDao;
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }

    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public ParamDao getParamDao() {
        return paramDao;
    }

    public void setParamDao(ParamDao paramDao) {
        this.paramDao = paramDao;
    }

    public ParamValueDao getParamValueDao() {
        return paramValueDao;
    }

    public void setParamValueDao(ParamValueDao paramValueDao) {
        this.paramValueDao = paramValueDao;
    }

    public PillDao getPillDao() {
        return pillDao;
    }

    public void setPillDao(PillDao pillDao) {
        this.pillDao = pillDao;
    }

    public UsePillDao getUsePillDao() {
        return usePillDao;
    }

    public void setUsePillDao(UsePillDao usePillDao) {
        this.usePillDao = usePillDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public AchievementDao getAchievementDao() {
        return achievementDao;
    }

    public void setAchievementDao(AchievementDao achievementDao) {
        this.achievementDao = achievementDao;
    }

    public UserAchievementDao getUserAchievementDao() {
        return userAchievementDao;
    }

    public void setUserAchievementDao(UserAchievementDao userAchievementDao) {
        this.userAchievementDao = userAchievementDao;
    }

    public ContraindicationDao getContraindicationDao() {
        return contraindicationDao;
    }

    public void setContraindicationDao(ContraindicationDao contraindicationDao) {
        this.contraindicationDao = contraindicationDao;
    }

    public ContraindicationPillDao getContraindicationPillDao() {
        return contraindicationPillDao;
    }

    public void setContraindicationPillDao(ContraindicationPillDao contraindicationPillDao) {
        this.contraindicationPillDao = contraindicationPillDao;
    }

    public EffectPillDao getEffectPillDao() {
        return effectPillDao;
    }

    public void setEffectPillDao(EffectPillDao effectPillDao) {
        this.effectPillDao = effectPillDao;
    }

    //?????????????????????? ?????? ???????????????? ????????????????????
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
        courseDao = null;
        coursePillDao = null;
        courseUserDao = null;

    }


}

