package tetramap.util;

import tetramap.entity.types.LatLong;
import tetramap.entity.types.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Класс работы с LatLong взят из библиотеки mapsForge
 */
public final class LatLongUtils {
    public static final double EQUATORIAL_RADIUS = 6378137.0D;
    public static final double INVERSE_FLATTENING = 298.257223563D;
    public static final double POLAR_RADIUS = 6356752.3142D;
    public static final double LATITUDE_MAX = 90.0D;
    public static final double LATITUDE_MIN = -90.0D;
    public static final double LONGITUDE_MAX = 180.0D;
    public static final double LONGITUDE_MIN = -180.0D;
    private static final double CONVERSION_FACTOR = 1000000.0D;
    private static final String DELIMITER = ",";

    public static boolean contains(LatLong[] latLongs, LatLong latLong) {
        boolean result = false;
        int i = 0;

        for(int j = latLongs.length - 1; i < latLongs.length; j = i++) {
            if (latLongs[i].getLatitude() > latLong.getLatitude() != latLongs[j].getLatitude() > latLong.getLatitude() && latLong.getLongitude() < (latLongs[j].getLongitude() - latLongs[i].getLongitude()) * (latLong.getLatitude() - latLongs[i].getLatitude()) / (latLongs[j].getLatitude() - latLongs[i].getLatitude()) + latLongs[i].getLongitude()) {
                result = !result;
            }
        }

        return result;
    }

    public static boolean contains(List<LatLong> latLongs, LatLong latLong) {
        boolean result = false;
        int i = 0;

        for(int j = latLongs.size() - 1; i < latLongs.size(); j = i++) {
            if (((LatLong)latLongs.get(i)).getLatitude() > latLong.getLatitude() != ((LatLong)latLongs.get(j)).getLatitude() > latLong.getLatitude() && latLong.getLongitude() < (((LatLong)latLongs.get(j)).getLongitude() - ((LatLong)latLongs.get(i)).getLongitude()) * (latLong.getLatitude() - ((LatLong)latLongs.get(i)).getLatitude()) / (((LatLong)latLongs.get(j)).getLatitude() - ((LatLong)latLongs.get(i)).getLatitude()) + ((LatLong)latLongs.get(i)).getLongitude()) {
                result = !result;
            }
        }

        return result;
    }

    public static int degreesToMicrodegrees(double coordinate) {
        return (int)(coordinate * 1000000.0D);
    }

    public static LatLong destinationPoint(LatLong start, double distance, float bearing) {
        double theta = Math.toRadians((double)bearing);
        double delta = distance / 6378137.0D;
        double phi1 = Math.toRadians(start.getLatitude());
        double lambda1 = Math.toRadians(start.getLongitude());
        double phi2 = Math.asin(Math.sin(phi1) * Math.cos(delta) + Math.cos(phi1) * Math.sin(delta) * Math.cos(theta));
        double lambda2 = lambda1 + Math.atan2(Math.sin(theta) * Math.sin(delta) * Math.cos(phi1), Math.cos(delta) - Math.sin(phi1) * Math.sin(phi2));
        return new LatLong(Math.toDegrees(phi2), Math.toDegrees(lambda2));
    }

    public static double distance(LatLong latLong1, LatLong latLong2) {
        return Math.hypot(latLong1.getLongitude() - latLong2.getLongitude(), latLong1.getLatitude() - latLong2.getLatitude());
    }

    public static double distanceSegmentPoint(double startX, double startY, double endX, double endY, double pointX, double pointY) {
        Point nearest = nearestSegmentPoint(startX, startY, endX, endY, pointX, pointY);
        return Math.hypot(nearest.getX() - pointX, nearest.getY() - pointY);
    }

    public static LatLong fromString(String latLongString) {
        double[] coordinates = parseCoordinateString(latLongString, 2);
        return new LatLong(coordinates[0], coordinates[1]);
    }

    public static double latitudeDistance(int meters) {
        return (double)(meters * 360) / 4.007501668557849E7D;
    }

    public static double longitudeDistance(int meters, double latitude) {
        return (double)(meters * 360) / (4.007501668557849E7D * Math.cos(Math.toRadians(latitude)));
    }

    public static double microdegreesToDegrees(int coordinate) {
        return (double)coordinate / 1000000.0D;
    }

    public static Point nearestSegmentPoint(double startX, double startY, double endX, double endY, double pointX, double pointY) {
        double xDiff = endX - startX;
        double yDiff = endY - startY;
        double length2 = xDiff * xDiff + yDiff * yDiff;
        if (length2 == 0.0D) {
            return new Point(startX, startY);
        } else {
            double t = ((pointX - startX) * (endX - startX) + (pointY - startY) * (endY - startY)) / length2;
            if (t < 0.0D) {
                return new Point(startX, startY);
            } else {
                return t > 1.0D ? new Point(endX, endY) : new Point(startX + t * (endX - startX), startY + t * (endY - startY));
            }
        }
    }

    public static double[] parseCoordinateString(String coordinatesString, int numberOfCoordinates) {
        StringTokenizer stringTokenizer = new StringTokenizer(coordinatesString, ",", true);
        boolean isDelimiter = true;
        ArrayList tokens = new ArrayList(numberOfCoordinates);

        while(stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            isDelimiter = !isDelimiter;
            if (!isDelimiter) {
                tokens.add(token);
            }
        }

        if (isDelimiter) {
            throw new IllegalArgumentException("invalid coordinate delimiter: " + coordinatesString);
        } else if (tokens.size() != numberOfCoordinates) {
            throw new IllegalArgumentException("invalid number of coordinate values: " + coordinatesString);
        } else {
            double[] coordinates = new double[numberOfCoordinates];

            for(int i = 0; i < numberOfCoordinates; ++i) {
                coordinates[i] = Double.parseDouble((String)tokens.get(i));
            }

            return coordinates;
        }
    }

    public static double sphericalDistance(LatLong latLong1, LatLong latLong2) {
        double dLat = Math.toRadians(latLong2.getLatitude() - latLong1.getLatitude());
        double dLon = Math.toRadians(latLong2.getLongitude() - latLong1.getLongitude());
        double a = Math.sin(dLat / 2.0D) * Math.sin(dLat / 2.0D) + Math.cos(Math.toRadians(latLong1.getLatitude())) * Math.cos(Math.toRadians(latLong2.getLatitude())) * Math.sin(dLon / 2.0D) * Math.sin(dLon / 2.0D);
        double c = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
        return c * 6378137.0D;
    }

    public static double validateLatitude(double latitude) {
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
    }

    public static double vincentyDistance(LatLong latLong1, LatLong latLong2) {
        double f = 0.0033528106647474805D;
        double L = Math.toRadians(latLong2.getLongitude() - latLong1.getLongitude());
        double U1 = Math.atan((1.0D - f) * Math.tan(Math.toRadians(latLong1.getLatitude())));
        double U2 = Math.atan((1.0D - f) * Math.tan(Math.toRadians(latLong2.getLatitude())));
        double sinU1 = Math.sin(U1);
        double cosU1 = Math.cos(U1);
        double sinU2 = Math.sin(U2);
        double cosU2 = Math.cos(U2);
        double lambda = L;
        double iterLimit = 100.0D;
        double cosSqAlpha = 0.0D;
        double sinSigma = 0.0D;
        double cosSigma = 0.0D;
        double cos2SigmaM = 0.0D;
        double sigma = 0.0D;
        double sinLambda = 0.0D;
        double sinAlpha = 0.0D;
        double cosLambda = 0.0D;

        double lambdaP;
        double uSq;
        do {
            sinLambda = Math.sin(lambda);
            cosLambda = Math.cos(lambda);
            sinSigma = Math.sqrt(cosU2 * sinLambda * cosU2 * sinLambda + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
            if (sinSigma == 0.0D) {
                return 0.0D;
            }

            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
            sigma = Math.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
            cosSqAlpha = 1.0D - sinAlpha * sinAlpha;
            if (cosSqAlpha != 0.0D) {
                cos2SigmaM = cosSigma - 2.0D * sinU1 * sinU2 / cosSqAlpha;
            } else {
                cos2SigmaM = 0.0D;
            }

            uSq = f / 16.0D * cosSqAlpha * (4.0D + f * (4.0D - 3.0D * cosSqAlpha));
            lambdaP = lambda;
            lambda = L + (1.0D - uSq) * f * sinAlpha * (sigma + uSq * sinSigma * (cos2SigmaM + uSq * cosSigma * (-1.0D + 2.0D * cos2SigmaM * cos2SigmaM)));
        } while(Math.abs(lambda - lambdaP) > 1.0E-12D && --iterLimit > 0.0D);

        if (iterLimit == 0.0D) {
            return 0.0D;
        } else {
            uSq = cosSqAlpha * (Math.pow(6378137.0D, 2.0D) - Math.pow(6356752.3142D, 2.0D)) / Math.pow(6356752.3142D, 2.0D);
            double A = 1.0D + uSq / 16384.0D * (4096.0D + uSq * (-768.0D + uSq * (320.0D - 175.0D * uSq)));
            double B = uSq / 1024.0D * (256.0D + uSq * (-128.0D + uSq * (74.0D - 47.0D * uSq)));
            double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4.0D * (cosSigma * (-1.0D + 2.0D * cos2SigmaM * cos2SigmaM) - B / 6.0D * cos2SigmaM * (-3.0D + 4.0D * sinSigma * sinSigma) * (-3.0D + 4.0D * cos2SigmaM * cos2SigmaM)));
            double s = 6356752.3142D * A * (sigma - deltaSigma);
            return s;
        }
    }

    private LatLongUtils() {
        throw new IllegalStateException();
    }
}
