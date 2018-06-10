package de.hottenstein.krimirundgang;

import java.util.ArrayList;
import java.util.List;

public class TourInfo {
    protected String title;
    protected List<StopInfo> stopList;

    protected TourInfo(String title, List<StopInfo> stopList){
        this.title = title;
        this.stopList = stopList;
    }

    protected TourInfo(){
        this.title = "";
        this.stopList = new ArrayList<>();
    }
}
