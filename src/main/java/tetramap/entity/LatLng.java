package tetramap.entity;

/**
 * Represents a geographical point with a certain latitude and longitude.
 */
public class LatLng {

    private Double latitude;
    private Double longitude;

    /**
     * Constructs a new LatLng object.
     */
    public LatLng() {
        super();
    }

    /**
     * Constructs a new LatLng object.
     *
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    public LatLng(Double latitude, Double longitude) {
        super();

        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the latitude.
     *
     * @return The latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude The latitude.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return The longitude.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude The longitude.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
