package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Pill;
import com.example.iherb.db.entities.UsePill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class UsePillDao extends BaseDaoImpl<UsePill, Integer> {


    public UsePillDao(ConnectionSource connectionSource,
                   Class<UsePill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<UsePill> getAll() throws SQLException {
        return this.queryForAll();
    }
}