package tetramap.util;

import tetramap.entity.LatLong;

public class LatLongUtil {

    public static double sphericalDistance(LatLong latLong1, LatLong latLong2) {
        double dLat = Math.toRadians(latLong2.getLatitude() - latLong1.getLatitude());
        double dLon = Math.toRadians(latLong2.getLongitude() - latLong1.getLongitude());
        double a = Math.sin(dLat / 2.0D) * Math.sin(dLat / 2.0D) + Math.cos(Math.toRadians(latLong1.getLatitude())) * Math.cos(Math.toRadians(latLong2.getLongitude())) * Math.sin(dLon / 2.0D) * Math.sin(dLon / 2.0D);
        double c = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
        return c * 6378137.0D;
    }

/*    public static double validateLatitude(double latitude) {
        if (!Double.isNaN(latitude) && !(latitude < -90.0D) && !(latitude > 90.0D)) {
            return latitude;
        } else {
            throw new IllegalArgumentException("invalid latitude: " + latitude);
        }
    }

    public static double validateLongitude(double longitude) {
        if (!Double.isNaN(longitude) && !(longitude < -180.0D) && !(longitude > 180.0D)) {
            return longitude;
        } else {
            throw new IllegalArgumentException("invalid longitude: " + longitude);
        }
    }*/
}
