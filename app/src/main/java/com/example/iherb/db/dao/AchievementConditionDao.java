package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.AchievementCondition;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class AchievementConditionDao extends BaseDaoImpl<AchievementCondition, Integer> {


    public AchievementConditionDao(ConnectionSource connectionSource,
                          Class<AchievementCondition> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<AchievementCondition> getAll() throws SQLException {
        return this.queryForAll();
    }

}