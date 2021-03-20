package com.example.test;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public  interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();


    @Query("SELECT id,descrition,status FROM task where status= 'Active'")
    List<Task> getAllactive();


    @Query("SELECT id,descrition,status FROM task where status= 'completed'")
    List<Task> getAllComplete();


    @Query("DELETE FROM task where  status= 'completed'")
    void getAllDeleteCompleteStatus();

    @Insert
    void insert(Task task);

}
