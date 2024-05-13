package com.api.weatherapi.client;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import static com.api.weatherapi.util.UtilMethods.calculateDistance;

@Component
public class WeatherStationApiCall {


    public String findClosedStation(String url, double latitude, double longitude) {


        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> restTemplateForEntity = restTemplate.getForEntity(url, String.class);

        JSONObject jsonObject = new JSONObject(restTemplateForEntity.getBody());

        JSONArray features = jsonObject.getJSONArray("features");
        String closestStation = "";
        double minDistance = Double.MAX_VALUE;


        for (int i = 0; i < features.length(); i++) {
            JSONObject station = features.getJSONObject(i);
            JSONObject geometry = station.getJSONObject("geometry");
            JSONObject properties =  station.getJSONObject("properties");
            String stationId = (String)properties.get("stationIdentifier");
            JSONArray coordinates = geometry.getJSONArray("coordinates");


            double stationLatitude = coordinates.getDouble(0);
            double stationLongitude = coordinates.getDouble(1);


            double distance = calculateDistance(latitude, longitude, stationLatitude, stationLongitude);
            if (distance < minDistance) {
                minDistance = distance;
                closestStation = stationId;
            }
        }
        return closestStation;

    }

}

