package de.hottenstein.krimirundgang;


import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andi on 20.08.2017.
 */

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.StopViewHolder> {
    private List<StopInfo> stopList;
    private TourDetailActivity tourDetailActivity;

    public StopAdapter(List<StopInfo> stopList, TourDetailActivity tourDetailActivity) {
        this.stopList = stopList;
        this.tourDetailActivity = tourDetailActivity;
    }

    @Override
    public int getItemCount(){
        return stopList.size();
    }

    @Override
    public void onBindViewHolder(StopViewHolder stopViewHolder, int i){
        StopInfo si = stopList.get(i);
        stopViewHolder.vTitle.setText(si.title);
        stopViewHolder.vDescription.setText(si.description);
        stopViewHolder.vCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tourDetailActivity, StopDetailActivity.class);
                tourDetailActivity.startActivity(intent);
            }
        });
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.stop_view, viewGroup, false);

        return new StopViewHolder(itemView);
    }

    public static class StopViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTitle;
        protected TextView vDescription;
        protected CardView vCard;

        public StopViewHolder(View v){
            super(v);
            vTitle = (TextView) v.findViewById(R.id.stopTitle);
            vDescription = (TextView) v.findViewById(R.id.stopDescription);
            vCard = (CardView) v.findViewById(R.id.stopView);

        }
    }
}
