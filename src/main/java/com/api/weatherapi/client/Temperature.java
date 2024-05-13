package com.api.weatherapi.client;

import java.util.Objects;

public class Temperature {

    String unitCode;
    Float value;
    String temperatureType;

    public Temperature() {
    }

    public Temperature(String unitCode, Float value, String temperatureType) {
        this.unitCode = unitCode;
        this.value = value;
        this.temperatureType = temperatureType;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getTemperatureType() {
        return temperatureType;
    }

    public void setTemperatureType(String temperatureType) {
        this.temperatureType = temperatureType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(unitCode, that.unitCode) && Objects.equals(value, that.value) && Objects.equals(temperatureType, that.temperatureType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitCode, value, temperatureType);
    }
}