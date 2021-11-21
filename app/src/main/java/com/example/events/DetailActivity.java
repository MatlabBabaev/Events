package com.example.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.events.data.Event;
import com.example.events.data.MainViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageViewBigPoster;

    private TextView textViewName;
    private TextView textViewCity;
    private TextView textViewState;
    private TextView textViewStartDate;
    private TextView textViewEndDate;

    private int id;
    private MainViewModel viewModel;
    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewBigPoster = findViewById(R.id.imageViewBigPoster);
        textViewName = findViewById(R.id.textViewName);
        textViewCity = findViewById(R.id.textViewCity);
        textViewState = findViewById(R.id.textViewState);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewEndDate = findViewById(R.id.textViewEndDate);
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("id")) {
            id = intent.getIntExtra("id", -1);
        } else {
            finish();
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        event = viewModel.getEventById(id);
        Picasso.get().load(event.getIcon()).into(imageViewBigPoster);
        textViewName.setText(event.getName());
        textViewCity.setText(event.getCity());
        textViewState.setText(event.getState());
        textViewEndDate.setText(event.getEndDate());
        textViewStartDate.setText(event.getStartDate());
    }

}
