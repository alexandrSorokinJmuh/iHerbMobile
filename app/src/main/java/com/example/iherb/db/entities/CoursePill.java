package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoursePill extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (course_id) REFERENCES course(id)")
    Integer course_id;
    @Column(type = "integer", reference = "FOREIGN KEY (pill_id) REFERENCES pill(id), primary key (course_id, pill_id)")
    Integer pill_id;
    @Column
    String description;

    public CoursePill(Integer course_id, Integer pill_id, String description) {
        this.course_id = course_id;
        this.pill_id = pill_id;
        this.description = description;
    }
}
