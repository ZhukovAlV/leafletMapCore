package ru.oniip.tetraleafletmap.gui;

import ru.oniip.tetraleafletmap.draw.*;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.option.MapOptions;
import ru.oniip.tetraleafletmap.layer.Layer;
import ru.oniip.tetraleafletmap.layer.LayerGroup;
import ru.oniip.tetraleafletmap.layer.TileLayer;
import ru.oniip.tetraleafletmap.option.*;

import java.util.List;

/**
 * Leaflet методы для работы с картой, маркерами и тд.
 */
public interface Leaflet {

  /**
   * Инстанцирование карты.
   *
   * @param id DOM ID of a div element.
   * @return Map.
   */
  LeafletMap map(String id);

  /**
   * Инстанцирование карты.
   *
   * @param id         DOM ID of a div element.
   * @param mapOptions Map options.
   * @return Map.
   */
  LeafletMap map(String id, MapOptions mapOptions);

  /**
   * Инстанцирование тайлового слоя.
   *
   * @param url URL template.
   * @return Tile layer.
   */
  TileLayer tileLayer(String url);

  /**
   * Инстанцирование тайлового слоя.
   *
   * @param url              URL template.
   * @param tileLayerOptions Tile layer options.
   * @return Tile layer.
   */
  TileLayer tileLayer(String url, TileLayerOptions tileLayerOptions);

  /**
   * Инстанцирование маркера.
   *
   * @param latLng Geographical point.
   * @return Marker.
   */
  Marker marker(LatLng latLng);

  /**
   * Инстанцирование маркера.
   *
   * @param latLng        Geographical point.
   * @param markerOptions Marker options.
   * @return Marker.
   */
  Marker marker(LatLng latLng, MarkerOptions markerOptions);

  /**
   * Инстанцирование polyline.
   *
   * @param latLngs Geographical points.
   * @return Polyline.
   */
  Polyline polyline(List<LatLng> latLngs);

  /**
   * Инстанцирование polyline.
   *
   * @param latLngs         Geographical points.
   * @param polylineOptions Polyline options.
   * @return Polyline.
   */
  Polyline polyline(List<LatLng> latLngs, PolylineOptions polylineOptions);

  /**
   * Инстанцирование polygon.
   *
   * @param latLngs Geographical points.
   * @return Polygon.
   */
  Polygon polygon(List<LatLng> latLngs);

  /**
   * Инстанцирование polygon.
   *
   * @param latLngs        Geographical points.
   * @param polygonOptions Polygon options.
   * @return Polygon.
   */
  Polygon polygon(List<LatLng> latLngs, PolygonOptions polygonOptions);

  /**
   * Инстанцирование circle.
   *
   * @param latLng Geographical point.
   * @return Circle.
   */
  Circle circle(LatLng latLng);

  /**
   * Инстанцирование circle.
   *
   * @param latLng        Geographical point.
   * @param circleOptions Circle options.
   * @return Circle.
   */
  Circle circle(LatLng latLng, CircleOptions circleOptions);

  /**
   * Инстанцирование иконки.
   *
   * @param iconOptions Icon options.
   * @return Icon.
   */
  Icon icon(IconOptions iconOptions);

  /**
   * Инстанцирование высплывающего окна.
   *
   * @return Popup.
   */
  Popup popup();

  /**
   * Инстанцирование высплывающего окна.
   *
   * @param popupOptions Popup options.
   * @return Popup.
   */
  Popup popup(PopupOptions popupOptions);

  /**
   * Инстанцирование высплывающего окна.
   *
   * @param popupOptions Popup options.
   * @param layer        Layer.
   * @return Popup.
   */
  Popup popup(PopupOptions popupOptions, Layer layer);

  /**
   * Инстанцирование tooltip.
   *
   * @return Tooltip.
   */
  Tooltip tooltip();

  /**
   * Инстанцирование tooltip.
   *
   * @param tooltipOptions Tooltip options.
   * @return Tooltip.
   */
  Tooltip tooltip(TooltipOptions tooltipOptions);

  /**
   * Инстанцирование tooltip.
   *
   * @param tooltipOptions Tooltip options.
   * @param layer          Layer.
   * @return Tooltip.
   */
  Tooltip tooltip(TooltipOptions tooltipOptions, Layer layer);

  /**
   * Инстанцирование layer group.
   *
   * @return Layer group.
   */
  LayerGroup layerGroup();
}
