package com.example.tanvi.networking;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tanvi on 25-10-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

   Context context;
    ArrayList<Earthquake> resource;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> resource) {
        super(context,R.layout.earthquake_layout,resource);
        this.context=context;
        this.resource=resource;



    }
    static class ViewHolder {
        TextView mag,coords,loc,time,date;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {




        if (convertView == null) {
            convertView = View.inflate(context, R.layout.earthquake_layout, null);
            ViewHolder vh = new ViewHolder();

            vh.mag= (TextView) convertView.findViewById(R.id.magnitude);
            vh.coords= (TextView) convertView.findViewById(R.id.coords);

            vh.loc= (TextView) convertView.findViewById(R.id.location);
            vh.date= (TextView) convertView.findViewById(R.id.date);
            vh.time= (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(vh);
        }
        Earthquake e;
        e=resource.get(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.mag.setText(e.getMagnitude());

      String locationOffset,primaryLocation;

        String s=e.getLocation();
        if (s.contains("of")) {
            String[] parts =s.split("of ");
            locationOffset = parts[0] + "of";
            primaryLocation = parts[1];
        } else {
            locationOffset = "Near the";
            primaryLocation = s;
        }

        vh.loc.setText(primaryLocation);

        vh.coords.setText(locationOffset);
        Long d= Long.valueOf(e.getDate());

        vh.date.setText(formatDate(d));

        vh.time.setText(formatTime(d));


        // Get the appropriate background color based on the current earthquake magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) vh.mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int z=(int)(Double.parseDouble(e.getMagnitude()));
        int magnitudeColor = getMagnitudeColor(z);
        Log.i("COLOrR", "getView: "+z);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        return convertView;




    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    private String formatDate(Long d)
    {
        Date date1=new Date(d);
        SimpleDateFormat dateFormat=new SimpleDateFormat("MMM d, yyyy");
        String dateTodisplay=dateFormat.format(date1);

        return dateTodisplay;
    }
    private String formatTime(Long d)
    {
        Date date1=new Date(d);
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm aaa");
        String timeTodisplay=dateFormat.format(date1);

        return timeTodisplay;
    }

}
