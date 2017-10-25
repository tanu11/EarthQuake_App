package com.example.tanvi.networking;

/**
 * Created by tanvi on 25-10-2017.
 */

public class Earthquake {

    private String magnitude,location,date,url;

    public Earthquake(String magnitude, String location, String date,String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
    public String showData()
    {
        return magnitude+" "+location+" "+date+" .";
    }

}
