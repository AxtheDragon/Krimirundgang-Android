package de.hottenstein.krimirundgang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        StopAdapter sa = new StopAdapter(createList(10));
        recyclerView.setAdapter(sa);
    }

    private List<StopInfo> createList(int size) {

        List<StopInfo> result = new ArrayList<StopInfo>();
        for (int i=1; i <= size; i++){
            StopInfo si = new StopInfo();
            si.title = StopInfo.TITLE_PREFIX + i;
            si.description = StopInfo.DESCRIPTION_PREFIX + i;
            si.location = null;

            result.add(si);
        }
        return result;
    }
}