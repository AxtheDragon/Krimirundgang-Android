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

    protected static final String TITLE_PREFIX = "Title_";
    protected static final String DESCRIPTION_PREFIX = "DESCRIPTION_";


    protected StopInfo(Parcel in) {
        title = in.readString();
        description = in.readString();
        location = (Location) in.readValue(Location.class.getClassLoader());
    }

    protected StopInfo() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(location);
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
