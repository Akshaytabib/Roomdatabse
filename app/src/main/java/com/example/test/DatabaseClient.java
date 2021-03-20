package com.example.test;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private TaskDatabase taskDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        taskDatabase = Room.databaseBuilder(mCtx, TaskDatabase.class, "TaskDB").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public TaskDatabase getAppDatabase() {
        return taskDatabase;
    }

}
