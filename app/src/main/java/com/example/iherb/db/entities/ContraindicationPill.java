package com.example.iherb.db.entities;

import com.example.iherb.db.database.Column;
import com.example.iherb.db.database.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ContraindicationPill extends Entity {
    @Column(type = "integer", reference = "FOREIGN KEY (source_pill_id) REFERENCES pill(id)")
    Integer source_pill_id;
    @Column(type = "integer", reference = "FOREIGN KEY (affect_pill_id) REFERENCES pill(id), primary key (source_pill_id, affect_pill_id)")
    Integer affect_pill_id;

}
