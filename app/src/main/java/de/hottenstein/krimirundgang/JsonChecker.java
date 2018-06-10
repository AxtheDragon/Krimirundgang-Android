package de.hottenstein.krimirundgang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonChecker {
    public static boolean checkJson(String json) {
        Boolean valid = false;
        try {
            JSONObject jsonTour = new JSONObject(json);

            if (jsonTour.getString("Name").isEmpty()) { return valid; }
            if (jsonTour.getJSONArray("Stops").length() == 0) { return valid; }

            JSONArray stops = jsonTour.getJSONArray("Stops");
            ArrayList<Integer> orderValues = new ArrayList<>();
            for (int i = 0; i < stops.length(); i++) {
                JSONObject stop = stops.getJSONObject(i);
                if (!validateStop(stop)) { return valid; }
                Integer order = stop.getInt("Order");
                if (orderValues.contains(order)) { return valid; }
                orderValues.add(order);
            }

            valid = true;
        } catch (JSONException e) {
            valid = false;
            e.printStackTrace();
        }
        return valid;
    }

    private static boolean validateStop(JSONObject stop) {
        Boolean valid = false;
        try {
            if (stop.getString("Title").isEmpty()) { return valid; }
            if (stop.getString("Description").isEmpty()) { return valid; }
            if (stop.getString("Content").isEmpty()) { return valid; }
            if (!validateLocation(stop.getJSONObject("Location"))) { return valid; }

            valid = true;
        } catch (JSONException e) {
            valid = false;
            e.printStackTrace();
        }

        return valid;
    }

    private static boolean validateLocation(JSONObject location) {
        Boolean valid = false;
        try {
            Double latitude = location.getDouble("Latitude");
            if (latitude < 0 || latitude > 90) { return valid; }
            Double longitude = location.getDouble("Longitude");
            if (longitude < -180 || longitude > 180) { return valid; }

            valid = true;
        } catch (JSONException e) {
            valid = false;
            e.printStackTrace();
        }

        return valid;
    }
}
