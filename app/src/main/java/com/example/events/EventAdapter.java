package com.example.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.events.data.Event;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private List<Event> events;
    private OnPosterClickListener onPosterClickListener;
    private OnReachedEndListener onReachedEndListener;

    public EventAdapter(){
        events = new ArrayList<>();
    }

    interface OnPosterClickListener{
        void onPosterClick(int position);

    }
    public void setOnPosterClickListener(EventAdapter.OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }
    interface OnReachedEndListener{
        void onReachEnd();
    }

    public void setOnReachedEndListener(OnReachedEndListener onReachedEndListener) {
        this.onReachedEndListener = onReachedEndListener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        if(i>events.size()-3 && onReachedEndListener!=null){
            onReachedEndListener.onReachEnd();
        }
        Event event = events.get(i);
        Picasso.get().load(event.getIcon()).into(eventViewHolder.imageViewSmallPoster);
        eventViewHolder.textName.setText(String.format("name: %s", event.getName()));
        eventViewHolder.textCity.setText(String.format("city: %s",event.getCity()));
        eventViewHolder.textState.setText(String.format("state: %s",event.getState()));
        eventViewHolder.textEndDate.setText(String.format("end Date: %s",event.getEndDate()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView textName;
        private TextView textCity;
        private TextView textState;
        private TextView textEndDate;
        private ImageView  imageViewSmallPoster;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.icon);
            textName = itemView.findViewById(R.id.textName);
            textCity = itemView.findViewById(R.id.textCity);
            textState = itemView.findViewById(R.id.textState);
            textEndDate = itemView.findViewById(R.id.textEndDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onPosterClickListener!=null){
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                }
            }
            });
        }
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public void addEvents(List<Event>events){
        this.events.addAll(events);
        notifyDataSetChanged();
    }
    public List<Event> getEvents() {
        return events;
    }
}
