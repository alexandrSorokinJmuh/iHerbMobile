package com.example.iherb.db.dao;


import com.example.iherb.db.entities.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
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

    public boolean checkUserExisting(String login) throws SQLException {
        QueryBuilder<User, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq("login", login);
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery).size() > 0;
    }

    public User getUserByLoginAndPassword(String login, String password) throws SQLException{
        QueryBuilder<User, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq("login", login).and().eq("password", password);
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery).get(0);
    }

}
