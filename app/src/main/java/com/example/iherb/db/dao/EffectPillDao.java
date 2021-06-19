package com.example.iherb.db.dao;

import com.example.iherb.db.entities.ContraindicationPill;
import com.example.iherb.db.entities.EffectPill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class EffectPillDao extends BaseDaoImpl<EffectPill, Integer> {


    public EffectPillDao(ConnectionSource connectionSource,
                                   Class<EffectPill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<EffectPill> getAll() throws SQLException {
        return this.queryForAll();
    }

}