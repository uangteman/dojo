package com.sample.an.sample.service.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by cindy on 5/16/16.
 */
@Table(name="mr_know_ut", id = "id")
public class KnownSourceActiveRecordModel extends Model{

    @Column(name = "id")
    public int id;

    @Column(name = "know_name_id")
    public String name;

    public KnownSourceActiveRecordModel(){
        super();
    }

    public KnownSourceActiveRecordModel(String name, int id){
        this();
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
