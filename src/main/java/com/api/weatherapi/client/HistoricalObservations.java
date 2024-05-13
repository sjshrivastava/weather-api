package com.api.weatherapi.client;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HistoricalObservations {

    public  String getHistoricalObservation(String url, String stationId) {
        RestTemplate restTemplate = new RestTemplate();
        url  = url + "/" + stationId + "/observations";
        ResponseEntity<String> restTemplateForEntity = restTemplate.getForEntity(url, String.class);

        JSONObject jsonObject = new JSONObject(restTemplateForEntity.getBody());


        return restTemplateForEntity.getBody();
    }
}
