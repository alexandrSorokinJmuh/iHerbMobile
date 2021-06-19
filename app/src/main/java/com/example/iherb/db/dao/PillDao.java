package com.example.iherb.db.dao;

import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.Pill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class PillDao extends BaseDaoImpl<Pill, Integer> {


    public PillDao(ConnectionSource connectionSource,
                         Class<Pill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Pill> getAll() throws SQLException {
        return this.queryForAll();
    }
}