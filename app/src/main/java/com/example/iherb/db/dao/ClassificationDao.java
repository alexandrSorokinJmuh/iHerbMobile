package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.UserAchievement;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ClassificationDao extends BaseDaoImpl<Classification, Integer> {


    public ClassificationDao(ConnectionSource connectionSource,
                              Class<Classification> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Classification> getAll() throws SQLException {
        return this.queryForAll();
    }

}