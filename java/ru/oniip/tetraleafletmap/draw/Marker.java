package ru.oniip.tetraleafletmap.draw;

import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.layer.Layer;

/**
 * Marker is used to display clickable/draggable icons on the map.
 */
public interface Marker extends Layer {

  /**
   * Changes the marker position to the given point.
   *
   * @param latLng New marker position.
   */
  void setLatLng(LatLng latLng);

  /**
   * Changes the zIndex offset of the marker.
   *
   * @param offset zIndex offset.
   */
  void setZIndexOffset(Number offset);

  /**
   * Changes the marker icon.
   *
   * @param icon Icon.
   */
  void setIcon(Icon icon);

  /**
   * Changes the opacity of the marker.
   *
   * @param opacity Opacity.
   */
  void setOpacity(Number opacity);
}
