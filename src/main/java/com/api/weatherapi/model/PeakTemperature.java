package com.api.weatherapi.model;

import com.api.weatherapi.client.Temperature;

public class PeakTemperature {

    Temperature [] temperatures;

    public Temperature[] getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(Temperature[] temperatures) {
        this.temperatures = temperatures;
    }
}
