package com.example.events.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Data
@AllArgsConstructor
@Entity(tableName = "events")
public class Event {

    @PrimaryKey
    private int id;
    private String url;
    private String startDate;
    private String endDate;
    private String name;
    private String icon;
    private String objType;
    private String loginRequired;
    private String city;
    private String state;
}
