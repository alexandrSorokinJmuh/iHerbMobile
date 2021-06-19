package com.example.iherb.db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String firstName;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String lastName;

    @DatabaseField(dataType = DataType.DATE)
    private Date dateOfBirth;

    @DatabaseField(canBeNull = false, unique = true, dataType = DataType.STRING)
    private String login;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String password;


    public User(String firstName, String lastName, Date dateOfBirth, String login, String password, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
        this.sex = sex;
    }

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String sex;

}
