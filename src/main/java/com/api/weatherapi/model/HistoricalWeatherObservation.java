package com.api.weatherapi.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class HistoricalWeatherObservation {
    private ZonedDateTime timestamp;
    private double temperature;
    private double humidity;

    public HistoricalWeatherObservation() {
    }

    public HistoricalWeatherObservation(ZonedDateTime timestamp, double temperature, double humidity) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalWeatherObservation that = (HistoricalWeatherObservation) o;
        return Double.compare(that.temperature, temperature) == 0 && Double.compare(that.humidity, humidity) == 0 && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, temperature, humidity);
    }
}
