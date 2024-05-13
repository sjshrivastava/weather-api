package com.api.weatherapi.client;

import com.api.weatherapi.model.PeakTemperature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Component
public class DailyHighLowTemp {

    public Map findDailyHighLow(String url, String station) {


        RestTemplate restTemplate = new RestTemplate();

        url = url + "/" + station + "/observations";

        ResponseEntity<String> restTemplateForEntity = restTemplate.getForEntity(url, String.class);

        JSONObject jsonObject = new JSONObject(restTemplateForEntity.getBody());

        JSONArray features = jsonObject.getJSONArray("features");

        Map<String, PeakTemperature> temperatureMap = new HashMap<>();

        for (int i = 0; i < features.length(); i++) {

            PeakTemperature peakTemperature = new PeakTemperature();
            JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
            JSONObject maxTemperatureLast24Hours = properties.getJSONObject("maxTemperatureLast24Hours");
            String timestamp = (String)properties.get("timestamp");

            Float tempValue = maxTemperatureLast24Hours.get("value").toString().equals("null")? 0 : (Float) maxTemperatureLast24Hours.get("value");

            Temperature maxTemperature = new Temperature((String)maxTemperatureLast24Hours.get("unitCode"),
                    tempValue,
                    "maxTemperatureLast24Hours");
            Temperature[] temperatures = new Temperature[2];
            temperatures[0] = maxTemperature;

            //temperatureMap.put(timestamp, maxTemperature);

            JSONObject minTemperatureLast24Hours = properties.getJSONObject("minTemperatureLast24Hours");

             tempValue = minTemperatureLast24Hours.get("value").toString().equals("null")? 0 : (Float) minTemperatureLast24Hours.get("value");

            Temperature minTemperature = new Temperature((String)minTemperatureLast24Hours.get("unitCode"),
                    tempValue, "minTemperatureLast24Hours");
            temperatures[1] = minTemperature;
            peakTemperature.setTemperatures(temperatures);

            temperatureMap.put(timestamp, peakTemperature);

        }
        return temperatureMap;
    }
}
