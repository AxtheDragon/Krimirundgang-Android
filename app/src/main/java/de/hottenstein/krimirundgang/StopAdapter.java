package de.hottenstein.krimirundgang;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andi on 20.08.2017.
 */

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.StopViewHolder> {
    private List<StopInfo> stopList;

    public StopAdapter(List<StopInfo> stopList) {
        this.stopList = stopList;
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

        public StopViewHolder(View v){
            super(v);
            vTitle = (TextView) v.findViewById(R.id.stopTitle);
            vDescription = (TextView) v.findViewById(R.id.stopDescription);
        }
    }
}
