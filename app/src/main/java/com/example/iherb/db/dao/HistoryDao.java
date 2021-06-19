package com.example.iherb.db.dao;


import com.example.iherb.db.entities.History;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class HistoryDao extends BaseDaoImpl<History, Integer> {


    public HistoryDao(ConnectionSource connectionSource,
                      Class<History> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<History> getAll() throws SQLException {
        return this.queryForAll();
    }
}