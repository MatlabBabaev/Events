package com.example.events.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    private static EventDatabase database;
    private LiveData<List<Event>> events;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database=EventDatabase.getInstance(getApplication());
        events=database.eventDao().getAllEvent();

    }

    public Event getEventById(int id){
        try {
            return new GetEventTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }

    public void deleteAllEvents(){
        new DeleteEventTask().execute();
    }
    public void deleteEvent(Event event){ new DeleteTask().execute(event);}
    public void insertEvent(Event event){
        new InsertTask().execute(event);
    }

    private static class GetEventTask extends AsyncTask<Integer, Void, Event> {
        @Override
        protected Event doInBackground(Integer... integers) {
            if(integers!=null && integers.length>0){
                return database.eventDao().getEventById(integers[0]);
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Event, Void, Void>{
        @Override
        protected Void doInBackground(Event... movies) {
            if(movies!=null && movies.length>0){
                database.eventDao().deleteEvent(movies[0]);
            }
            return null;
        }
    }

    private static class InsertTask extends AsyncTask<Event, Void, Void>{
        @Override
        protected Void doInBackground(Event... movies) {
            if(movies!=null && movies.length>0){
                database.eventDao().insertEvent(movies[0]);
            }
            return null;
        }
    }

    private static class DeleteEventTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... integers) {
            database.eventDao().deleteAllEvent();
            return null;
        }
    }
}
