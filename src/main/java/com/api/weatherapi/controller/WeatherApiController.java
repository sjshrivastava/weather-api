package com.api.weatherapi.controller;

import com.api.weatherapi.aspect.RateLimit;
import com.api.weatherapi.client.DailyHighLowTemp;
import com.api.weatherapi.client.HistoricalObservations;
import com.api.weatherapi.client.WeatherStationApiCall;
import com.api.weatherapi.model.HistoricalWeatherObservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.api.weatherapi.util.Constants.*;

@RestController
public class WeatherApiController {

    @Autowired
    WeatherStationApiCall weatherStationApiCall;

    @Autowired
    HistoricalObservations historicalObservations;

    @Autowired
    DailyHighLowTemp dailyHighLowTemp;

    @Value("${weather.station.url}") String url;

    @RequestMapping(value = STATION_URL, method = RequestMethod.GET)
    @RateLimit
    public ResponseEntity<Object> getClosetWeatherStation(
            @RequestParam(name = "latitude") String latitude, @RequestParam(name =  "longitude") String longitude){

        if(!validGeoLocation(latitude, longitude)){
            return ResponseEntity.badRequest().body("invalid geo orbitals");
        }

        String closestStation = weatherStationApiCall.findClosedStation(url, Double.valueOf(latitude), Double.valueOf(longitude));
        return ResponseEntity.ok(closestStation);
    }

    @RequestMapping(value = STATION_OBSERVATION, method = RequestMethod.GET)
    @RateLimit
    public ResponseEntity<Object> getHistoricalObservations(
            @RequestParam(name = "station") String station){

        if(!validStation(station)){
            return ResponseEntity.badRequest().body("invalid station name");
        }

        return ResponseEntity.ok(historicalObservations.getHistoricalObservation(url, station));
    }

    @RequestMapping(value = DAILY_HIGH_LOW_TEMP, method = RequestMethod.GET)
    @RateLimit
    public ResponseEntity<Object> getDailyHighLowTemperature(
            @RequestParam(name = "station") String station){
        ResponseEntity mapResponseEntity = null;
        try {


            if (!validStation(station)) {
                return ResponseEntity.badRequest().body("invalid station name");
            }
            mapResponseEntity = ResponseEntity.ok(dailyHighLowTemp.findDailyHighLow(url, station));
        }
        catch (Exception ex){
            ResponseEntity.ok(ex.getMessage());
        }
        return mapResponseEntity;
    }

    private boolean validGeoLocation(String latitude, String longitude) {
        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);

            if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
                return false;
            }
            return true;
        }
        catch (Exception ex){
            System.out.println("exception in validating geo request " + ex.getMessage());
            return false;
        }
    }

    private boolean validStation(String station){

        if(station == null){
            return false;
        }

        return true;
    }

}
