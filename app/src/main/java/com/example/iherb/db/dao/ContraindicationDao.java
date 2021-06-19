package com.example.iherb.db.dao;

import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.Effect;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ContraindicationDao extends BaseDaoImpl<Contraindication, Integer> {


    public ContraindicationDao(ConnectionSource connectionSource,
                     Class<Contraindication> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Contraindication> getAll() throws SQLException {
        return this.queryForAll();
    }

}