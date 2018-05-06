package de.hottenstein.krimirundgang;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andi on 20.08.2017.
 */

public class StopInfo implements Parcelable {
    protected String title;
    protected String description;
    protected Location location;
    protected String content;
    protected Integer order;

    protected static final String TITLE_PREFIX = "Title_";
    protected static final String DESCRIPTION_PREFIX = "DESCRIPTION_";
    protected static final String CONTENT_DUMMY = "_Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut"
                                                    +"labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores"
                                                    + "et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem"
                                                    + " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore"
                                                    + " et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum."
                                                    + " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

    protected StopInfo(Parcel in) {
        // We need to make sure that the assignment order equals the write order of writeToParcel
        //That can be tested with "StopInfo Test"
        title = in.readString();
        description = in.readString();
        location = (Location) in.readValue(Location.class.getClassLoader());
        content = in.readString();
        order = in.readInt();
    }

    protected StopInfo(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // We need to make sure that the write order equals the assignment order of the constructor
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(location);
        dest.writeString(content);
        dest.writeInt(order);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StopInfo> CREATOR = new Parcelable.Creator<StopInfo>() {
        @Override
        public StopInfo createFromParcel(Parcel in) {
            return new StopInfo(in);
        }

        @Override
        public StopInfo[] newArray(int size) {
            return new StopInfo[size];
        }
    };
}
