package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.ContraindicationPill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ContraindicationPillDao extends BaseDaoImpl<ContraindicationPill, Integer> {


    public ContraindicationPillDao(ConnectionSource connectionSource,
                               Class<ContraindicationPill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<ContraindicationPill> getAll() throws SQLException {
        return this.queryForAll();
    }

}