package com.api.weatherapi.model;

import java.util.Objects;

public class WeatherStationPojo {
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    Geometry geometry;
    String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherStationPojo that = (WeatherStationPojo) o;
        return Objects.equals(geometry, that.geometry) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(geometry, id);
    }
}
