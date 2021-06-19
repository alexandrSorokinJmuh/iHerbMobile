package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User extends Entity {
    @DatabaseField(generatedId = true)
    private int Id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(type = "integer")
    private Integer dateOfBirth;

    @Column
    private String login;

    @Column
    private String password;


    @Column
    private String sex;

    public User(String firstName, String lastName, Integer dateOfBirth, String login, String password, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
        this.sex = sex;
    }
}
