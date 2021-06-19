package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Achievement;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class AchievementDao extends BaseDaoImpl<Achievement, Integer> {


    public AchievementDao(ConnectionSource connectionSource,
                              Class<Achievement> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Achievement> getAll() throws SQLException {
        return this.queryForAll();
    }

}
