package ru.oniip.tetraleafletmap.util;

import ru.oniip.tetraleafletmap.entity.LatLng;

public class LatLongUtils {

    /**
     * Вычисление радиуса по 2 координатам
     * @param latLong1 LatLng1
     * @param latLong2 LatLng2
     * @return радиус
     */
    public static double sphericalDistance(LatLng latLong1, LatLng latLong2) {
        double dLat = Math.toRadians(latLong2.getLat() - latLong1.getLat());
        double dLon = Math.toRadians(latLong2.getLng() - latLong1.getLng());
        double a = Math.sin(dLat / 2.0D) * Math.sin(dLat / 2.0D) + Math.cos(Math.toRadians(latLong1.getLat())) * Math.cos(Math.toRadians(latLong2.getLng())) * Math.sin(dLon / 2.0D) * Math.sin(dLon / 2.0D);
        double c = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
        return c * 6378137.0D;
    }
}
