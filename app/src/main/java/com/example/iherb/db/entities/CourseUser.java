package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseUser extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (user_id) REFERENCES user(id)")
    Integer user_id;
    @Column(type = "integer", reference = "FOREIGN KEY (course_id) REFERENCES course(id), primary key (user_id, course_id)")
    Integer course_id;
    @Column(type = "integer")
    Integer date_begin;

    public CourseUser(Integer user_id, Integer course_id, Integer dateBegin) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.date_begin = dateBegin;
    }
}
