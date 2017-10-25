package com.example.tanvi.networking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FetchData.FetchDataInterface {
    private static final String TAG ="MainAct" ;
    EarthquakeAdapter adapter;
    ArrayList<Earthquake> earthquakeList;
    String Url="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=0&limit=20";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.EarthquakeList);
        earthquakeList=new ArrayList<>();
        FetchData fetchData=new FetchData();
        fetchData.execute(Url);

        fetchData.setFetchDataInterfaceListener(this);




        adapter=new EarthquakeAdapter(MainActivity.this,earthquakeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),  adapter.getItem(position).getLocation(),Toast.LENGTH_SHORT).show();

                Intent i = new Intent();
                i.setClass(getApplicationContext(), DetailedActivity.class);
                i.putExtra("message",earthquakeList.get(position).getUrl());
                startActivity(i);
            }
        });



    }

    @Override
    public void onTaskComplete(ArrayList<Earthquake> earthquakes) {
        this.earthquakeList=earthquakes;
        if(this.earthquakeList!=null)
        { adapter.clear();
        adapter.addAll(earthquakes);}
    }
}
