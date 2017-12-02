package de.hottenstein.krimirundgang;

import android.location.Location;
import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Alex on 02.12.2017.
 */
public class StopInfoTest {
    String title = "Test title";
    String description = "Test description";
    Location location = new Location("Test location");
    String content = "Test content";
    StopInfo stopInfo;

    @Before
    public void initalize() {
        title = "Test title";
        description = "Test description";
        location = new Location("Test location");
        content = "Test content";

        stopInfo = new StopInfo();
        stopInfo.title = title;
        stopInfo.description = description;
        stopInfo.location = location;
        stopInfo.content = content;
    }

    /**
     * This test makes sure that the StopInfo is written to and read from the parcel in the correct
     * order, when we send it to an other activity via an intent.
     */
    @Test
    public void writeToParcelOrder() throws Exception {
        Parcel mockParcel = mock(Parcel.class);
        InOrder inOrder = inOrder(mockParcel);

        stopInfo.writeToParcel(mockParcel, 0);

        inOrder.verify(mockParcel).writeString(title);
        inOrder.verify(mockParcel).writeString(description);
        inOrder.verify(mockParcel).writeValue(location);
        inOrder.verify(mockParcel).writeString(content);
    }

    @Test
    public void constructorAssignmentOrder() throws Exception {
        Parcel mockParcel = mock(Parcel.class);
        InOrder inOrder = inOrder(mockParcel);

        StopInfo unusedInfo = new StopInfo(mockParcel);

        inOrder.verify(mockParcel, times(2)).readString();
        inOrder.verify(mockParcel).readValue(Location.class.getClassLoader());
        inOrder.verify(mockParcel).readString();
    }
}
