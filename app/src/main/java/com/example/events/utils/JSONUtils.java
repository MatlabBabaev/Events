package com.example.events.utils;

import com.example.events.data.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {

    private static final String KEY_RESULTS = "data";
    private static final String KEY_ID = "id";
    private static final String KEY_URL = "url";
    private static final String KEY_START_DATE = "startDate";
    private static final String KEY_END_DATE = "endDate";
    private static final String KEY_NAME = "name";
    private static final String KEY_ICON_PATH = "icon";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_OBJ_TYPE = "objType";
    private static final String KEY_LOGIN_REQUIRED = "loginRequired";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";

    public static ArrayList<Event> getEventsFromJSON(JSONObject jsonObject){
        ArrayList<Event> result = new ArrayList<>();
        String[] City = new String[]{"Sydney", "Melbourne","Perth","Darwin","Adelaide","New-York","Miami","Los-Angeles","Texas","San-Francisco","Washington","Sydney","Sydney","Wollongong","NewCastle"};
        String[] State = new String[]{"NSW", "VIC","WA","NT","WA","NY","FL","CF","TS","SF","WT","NSW","NSW","NSW","NSW"};
        if(jsonObject==null){
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESULTS);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject objectEvent = jsonArray.getJSONObject(i);
                int id =i;
                String url = objectEvent.getString(KEY_URL);
                String stratDate = objectEvent.getString(KEY_START_DATE);
                String endDate = objectEvent.getString(KEY_END_DATE);
                String name = objectEvent.getString(KEY_NAME);
                String iconPath = objectEvent.getString(KEY_ICON_PATH);
                String venue = objectEvent.getString(KEY_VENUE);
                String objType = objectEvent.getString(KEY_OBJ_TYPE);
                String loginRequired = objectEvent.getString(KEY_LOGIN_REQUIRED);
                String city = City[i];
                String state = State[i];

                Event event = new Event(id, url,stratDate,endDate, name, iconPath, objType,loginRequired, city, state);
                result.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
