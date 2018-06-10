package de.hottenstein.krimirundgang;

import android.location.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TourLoader {
    public static TourInfo loadTour() {
        InputStream inputStream = getResources().openRawResource(R.raw.example_tour);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }

            String jsonString = sb.toString();

            JSONObject jsonTour = new JSONObject(jsonString);
            String name = jsonTour.getString("Name");
            JSONArray stops = jsonTour.getJSONArray("Stops");
            return new TourInfo(name, readStops(stops));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<StopInfo> readStops(JSONArray wrappedStops) {
        List<StopInfo> stopList = new ArrayList<>();
        JSONObject currentStop;
        for (int i = 0; i < wrappedStops.length(); i++) {
            try {
                currentStop = wrappedStops.getJSONObject(i);
                StopInfo newStop = new StopInfo();
                newStop.title = currentStop.getString("Title");
                newStop.description = currentStop.getString("Description");
                JSONObject jsonLocation = currentStop.getJSONObject("Location");
                newStop.location = new Location("JSON");
                newStop.location.setLatitude(jsonLocation.getDouble("Latitude"));
                newStop.location.setLongitude(jsonLocation.getDouble("Longitude"));
                newStop.content = currentStop.getString("Content");
                newStop.order = currentStop.getInt("Order");
                stopList.add(newStop);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stopList;
    }
}
