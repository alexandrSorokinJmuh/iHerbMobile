package com.example.iherb.db.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iherb.db.HelperFactory;
import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Effect;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements Serializable {

    SQLiteDatabase db;
    String tableName;

    public UserDao(SQLiteDatabase db) {
        this.db = db;
        tableName = TableResolver.getTableName(User.class);
    }

    public User getById(String id) {
        String table = String.format("%s as user", tableName);
        String[] columns = {"user.id as id", "user.firstName as firstName", "user.lastName as lastName", "user.dateOfBirth as dateOfBirth", "user.login as login", "user.password", "user.sex as sex"};
        String selection = "user.id = ?";
        String[] selectionArgs = {id};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        User user = null;
        if (c.moveToFirst()) {
            user = new User();
            user.setId(c.getInt(c.getColumnIndex("id")));
            user.setFirstName(c.getString(c.getColumnIndex("firstName")));
            user.setLastName(c.getString(c.getColumnIndex("lastName")));
            user.setDateOfBirth(c.getInt(c.getColumnIndex("dateOfBirth")));
            user.setLogin(c.getString(c.getColumnIndex("login")));
            user.setPassword(c.getString(c.getColumnIndex("password")));
            user.setSex(c.getString(c.getColumnIndex("sex")));

        }
        c.close();
        return user;
    }

    public long create(User user) {
        ContentValues cv = new ContentValues();
        cv.put("id", user.getId());
        cv.put("firstName", user.getFirstName());
        cv.put("lastName", user.getLastName());
        cv.put("dateOfBirth", user.getDateOfBirth());
        cv.put("login", user.getLogin());
        cv.put("password", user.getPassword());
        cv.put("sex", user.getSex());
        return db.insert(tableName, null, cv);
    }

    public int delete(String id) {
        if (id.isEmpty()) {
            return -1;
        }
        return db.delete(tableName, "id = " + id,
                null);
    }

    public User getUserByLogin(String login) {
        String table = String.format("%s as user", tableName);
        String[] columns = {"user.id as id", "user.firstName as firstName", "user.lastName as lastName", "user.dateOfBirth as dateOfBirth", "user.login as login", "user.password", "user.sex as sex"};
        String selection = "user.firstName = ?";
        String[] selectionArgs = {login};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        User user = null;
        if (c.moveToFirst()) {
            user = new User();
            user.setId(c.getInt(c.getColumnIndex("id")));
            user.setFirstName(c.getString(c.getColumnIndex("firstName")));
            user.setLastName(c.getString(c.getColumnIndex("lastName")));
            user.setDateOfBirth(c.getInt(c.getColumnIndex("dateOfBirth")));
            user.setLogin(c.getString(c.getColumnIndex("login")));
            user.setPassword(c.getString(c.getColumnIndex("password")));
            user.setSex(c.getString(c.getColumnIndex("sex")));
        }
        c.close();
        return user;
    }

    public User getUserByLoginAndPass(String login, String password) {
        String table = String.format("%s as user", tableName);
        String[] columns = {"user.id as id", "user.firstName as firstName", "user.lastName as lastName", "user.dateOfBirth as dateOfBirth", "user.login as login", "user.password", "user.sex as sex"};
        String selection = "user.firstName = ? and user.password = ?";
        String[] selectionArgs = {login, password};
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, "id");


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        User user = null;
        if (c.moveToFirst()) {
            user = new User();
            user.setId(c.getInt(c.getColumnIndex("id")));
            user.setFirstName(c.getString(c.getColumnIndex("firstName")));
            user.setLastName(c.getString(c.getColumnIndex("lastName")));
            user.setDateOfBirth(c.getInt(c.getColumnIndex("dateOfBirth")));
            user.setLogin(c.getString(c.getColumnIndex("login")));
            user.setPassword(c.getString(c.getColumnIndex("password")));
            user.setSex(c.getString(c.getColumnIndex("sex")));
        }
        c.close();
        return user;
    }

}