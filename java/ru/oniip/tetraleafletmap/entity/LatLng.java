package ru.oniip.tetraleafletmap.entity;

/**
 * Represents a geographical point with a certain latitude and longitude.
 */
public class LatLng {

  private float lat;
  private float lng;

  /**
   * Constructs a new LatLng object.
   */
  public LatLng() {
    super();
  }

  /**
   * Constructs a new LatLng object.
   *
   * @param lat Latitude.
   * @param lng Longitude.
   */
  public LatLng(float lat, float lng) {
    super();

    this.lat = lat;
    this.lng = lng;
  }

  /**
   * Gets the latitude.
   *
   * @return The latitude.
   */
  public float getLat() {
    return lat;
  }

  /**
   * Sets the latitude.
   *
   * @param lat The latitude.
   */
  public void setLat(float lat) {
    this.lat = lat;
  }

  /**
   * Gets the longitude.
   *
   * @return The longitude.
   */
  public float getLng() {
    return lng;
  }

  /**
   * Sets the longitude.
   *
   * @param lng The longitude.
   */
  public void setLng(float lng) {
    this.lng = lng;
  }
}
