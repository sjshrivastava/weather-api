package com.api.weatherapi.util;

public  class UtilMethods {

  public static  double calculateDistance(double startLat, double startLong, double endLat, double endLong) {

        double EARTH_RADIUS = 6371;

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = Math.sin(dLat/2)*Math.sin(dLat/2) + Math.cos(startLat) * Math.cos(endLat)
                 * Math.sin(dLong/2)*Math.sin(dLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
