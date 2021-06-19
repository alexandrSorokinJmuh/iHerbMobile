package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ParamValueDao extends BaseDaoImpl<ParamValue, Integer> {


    public ParamValueDao(ConnectionSource connectionSource,
                    Class<ParamValue> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<ParamValue> getAll() throws SQLException {
        return this.queryForAll();
    }
}