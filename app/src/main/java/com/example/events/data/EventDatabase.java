package com.example.events.data;
import android.content.Context;
import android.content.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class}, version = 2, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase{

    private static EventDatabase database;
    private static final String DB_NAME="events";
    private static final Object LOCK = new Object();

    public static EventDatabase getInstance(Context context){

        synchronized (LOCK){
            if(database==null){
                database=Room.databaseBuilder(context, EventDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
        }
        return database;
    }
    public abstract EventDao eventDao();
}
