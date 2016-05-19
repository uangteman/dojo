package com.sample.an.sample.service.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by cindy on 5/13/16.
 */
@DatabaseTable(tableName = "mr_know_ut")
public class KnownSourceORMLiteModel implements KeyModel<Integer>, Serializable {

    @DatabaseField(columnName = "id", generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "know_name_id")
    private String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
