package com.example.test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "descrition")
    String descrition;
    @ColumnInfo(name = "status")
    String  active;
//    @ColumnInfo(name = "comlted")
//    String  comlted;

    public Task() {
    }

    public Task(int id, String descrition, String active, String comlted) {
        this.id = id;
        this.descrition = descrition;
        this.active = active;
//        this.comlted = comlted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

//    public String getComlted() {
//        return comlted;
//    }
//
//    public void setComlted(String comlted) {
//        this.comlted = comlted;
//    }
}
