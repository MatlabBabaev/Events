package com.example.events.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EventDao {

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvent();

    @Query("SELECT * FROM events WHERE id == :eventId")
    Event getEventById(int eventId);

    @Query("DELETE FROM events")
    void deleteAllEvent();

    @Insert()
    void insertEvent(Event event);

    @Delete()
    void deleteEvent(Event event);

}
