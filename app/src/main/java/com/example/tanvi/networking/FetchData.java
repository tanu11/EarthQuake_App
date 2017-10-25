package com.example.tanvi.networking;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tanvi on 25-10-2017.
 */

public class FetchData extends AsyncTask<String,Void,ArrayList<Earthquake>>   {


    private static final String TAG = "INFETCHDATA";
    private static final String TAG1 ="FeatureLength" ;
    FetchDataInterface listener;


    @Override
    protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
        super.onPostExecute(earthquakes);
        if(listener!=null)
            listener.onTaskComplete(earthquakes);
    }

    @Override
    protected ArrayList<Earthquake> doInBackground(String... params) {
        String urlString=params[0];//param[0] main url hai
        Log.i(TAG, "doInBackground: ");
        try {
            URL url=new URL(urlString);//url object with this
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");//we are taking data so get
            urlConnection.connect();//connection established with net


            InputStream inputStream=urlConnection.getInputStream();
            if(inputStream==null)
            {
                return null;
            }
            Scanner s=new Scanner(inputStream);
            StringBuffer output=new StringBuffer();
            while(s.hasNext())
            {output.append(s.nextLine());
            }

            Log.i("jsondata",output.toString());//output was string buffer



            return parseData(output.toString());


        } catch (MalformedURLException e) {return null;

        } catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }


    }


    private ArrayList<Earthquake> parseData(String Data) throws JSONException {
        JSONObject obj=new JSONObject(Data);
        JSONArray feature=obj.getJSONArray("features");
        Log.i("HELLO","inside "+feature.length());
        ArrayList<Earthquake> arrayList=new ArrayList<Earthquake>();
        for(int i=0;i<feature.length();i++){

            JSONObject y=feature.getJSONObject(i);
            JSONObject x=y.getJSONObject("properties");


            String a=x.getString("mag");

            String b=x.getString("place");

            String c=x.getString("time");
            String url=x.getString("url");

            Log.i("HELLO","inside "+i);

            arrayList.add(new Earthquake(a,b,c,url));



        }

        return arrayList;



    }


    public interface FetchDataInterface
    {
        void onTaskComplete(ArrayList<Earthquake> earthquakes);
    }
    public void setFetchDataInterfaceListener(FetchDataInterface listener)
    {
        this.listener=listener;
    }


}

