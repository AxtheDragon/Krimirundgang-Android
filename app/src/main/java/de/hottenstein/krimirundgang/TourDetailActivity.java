package de.hottenstein.krimirundgang;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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

public class TourDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tourCycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        TourInfo myTour = loadTour();

        StopAdapter sa = new StopAdapter(myTour.stopList, this);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(myTour.title);
        recyclerView.setAdapter(sa);
    }

    public static List<StopInfo> createList(int size) {

        List<StopInfo> result = new ArrayList<StopInfo>();
        for (int i=1; i <= size; i++){
            StopInfo si = new StopInfo();
            si.title = StopInfo.TITLE_PREFIX + i;
            si.description = StopInfo.DESCRIPTION_PREFIX + i;
            si.location = new Location("");
            si.location.setLatitude(52.0000);
            si.location.setLongitude(9.0000);
            si.content = i + StopInfo.CONTENT_DUMMY;

            result.add(si);
        }
        return result;
    }

    private TourInfo loadTour() {
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

    private List<StopInfo> readStops(JSONArray wrappedStops) {
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