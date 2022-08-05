package ru.oniip.tetraleafletmap.draw;

import ru.oniip.tetraleafletmap.entity.LatLng;

/**
 * A circle of a fixed size with radius specified in pixels.
 */
public interface Circle extends Path {

  /**
   * Sets the position of a circle to a new location.
   *
   * @param latLng The new location.
   */
  void setCenter(LatLng latLng);

  /**
   * Sets the radius of a circle. Units are in pixels.
   *
   * @param radius Radius in pixels.
   */
  void setRadius(Number radius);
}
