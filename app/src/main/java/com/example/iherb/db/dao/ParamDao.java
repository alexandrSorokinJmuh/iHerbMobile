package com.example.iherb.db.dao;

import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ParamDao extends BaseDaoImpl<Param, Integer> {


    public ParamDao(ConnectionSource connectionSource,
                      Class<Param> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Param> getAll() throws SQLException {
        return this.queryForAll();
    }
}