package com.example.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.events.data.Event;
import com.example.events.data.MainViewModel;
import com.example.events.utils.JSONUtils;
import com.example.events.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEvents;
    private EventAdapter eventAdapter;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        recyclerViewEvents = findViewById(R.id.recyclerViewEvents);
        recyclerViewEvents.setLayoutManager(new GridLayoutManager(this, 1));
        eventAdapter = new EventAdapter();
        if(eventAdapter.getItemCount()==0)
            downloadData(1);

        recyclerViewEvents.setAdapter(eventAdapter);

        eventAdapter.setOnPosterClickListener(new EventAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                Event event = eventAdapter.getEvents().get(position);
               // Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //intent.putExtra("id", event.getId());
                //startActivity(intent);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://guidebook.com" + event.getUrl()));
                startActivity(browserIntent);
            }
        });
        eventAdapter.setOnReachedEndListener(new EventAdapter.OnReachedEndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(MainActivity.this, "End of list", Toast.LENGTH_SHORT).show();
            }
        });
        LiveData<List<Event>> eventsFromData = viewModel.getEvents();
        eventsFromData.observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                eventAdapter.setEvents(events);
            }
        });

    }

    private void downloadData(int methodOfSort){
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(methodOfSort);
        ArrayList<Event> events = JSONUtils.getEventsFromJSON(jsonObject);
        if(events!=null && !events.isEmpty()){
            viewModel.deleteAllEvents();
            for (Event event: events){
                viewModel.insertEvent(event);
            }
        }
    }
}