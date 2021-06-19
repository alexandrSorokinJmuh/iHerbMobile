package com.example.iherb.db.dao;

import com.example.iherb.db.entities.User;
import com.example.iherb.db.entities.UserAchievement;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class UserAchievementDao extends BaseDaoImpl<UserAchievement, Integer> {


    public UserAchievementDao(ConnectionSource connectionSource,
                   Class<UserAchievement> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<UserAchievement> getAll() throws SQLException {
        return this.queryForAll();
    }

}
