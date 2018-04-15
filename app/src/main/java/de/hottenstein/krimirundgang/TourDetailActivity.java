package de.hottenstein.krimirundgang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.location.Location;
import android.view.View;

public class TourDetailActivity extends AppCompatActivity {

    private static String sLocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tourCycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        StopAdapter sa = new StopAdapter(createList(10), this);
        recyclerView.setAdapter(sa);
    }

    public static List<StopInfo> createList(int size) {

        List<StopInfo> result = new ArrayList<StopInfo>();
        for (int i=1; i <= size; i++){
            StopInfo si = new StopInfo();
            si.title = StopInfo.TITLE_PREFIX + i;
            si.description = StopInfo.DESCRIPTION_PREFIX + i;
            si.location = new Location(sLocationName);
            si.location.setLatitude(52.0000);
            si.location.setLongitude(9.0000);
            si.content = i + StopInfo.CONTENT_DUMMY;

            result.add(si);
        }
        return result;
    }
}