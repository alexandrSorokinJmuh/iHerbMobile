package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.Effect;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class EffectDao extends BaseDaoImpl<Effect, Integer> {


    public EffectDao(ConnectionSource connectionSource,
                             Class<Effect> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Effect> getAll() throws SQLException {
        return this.queryForAll();
    }

}