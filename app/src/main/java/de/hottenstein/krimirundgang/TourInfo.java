package de.hottenstein.krimirundgang;

import java.util.List;

public class TourInfo {
    protected String TourTitle;
    protected List<StopInfo> stopList;

    protected TourInfo(){
        this.TourTitle = "MyFirstTour";
        this.stopList = TourDetailActivity.createList(10);
    }
}
