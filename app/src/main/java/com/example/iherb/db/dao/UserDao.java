package com.example.iherb.db.dao;


import com.example.iherb.db.entities.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDaoImpl<User, Integer> {


    public UserDao(ConnectionSource connectionSource,
                      Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<User> getAll() throws SQLException {
        return this.queryForAll();
    }

}
