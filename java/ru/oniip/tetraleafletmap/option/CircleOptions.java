package ru.oniip.tetraleafletmap.option;

/**
 * CircleMarker options.
 */
public class CircleOptions extends PathOptions {

  private Number radius;

  /**
   * Gets the radius of the circle.
   *
   * @return Radius in pixels.
   */
  public Number getRadius() {
    return radius;
  }

  /**
   * Sets the radius of the circle.
   *
   * @param radius Radius in pixels.
   */
  public void setRadius(Number radius) {
    this.radius = radius;
  }
}
