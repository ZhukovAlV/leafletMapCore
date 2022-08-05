package ru.oniip.tetraleafletmap.gui;

import ru.oniip.tetraleafletmap.layer.LayerGroup;
import ru.oniip.tetraleafletmap.layer.TileLayer;
import ru.oniip.tetraleafletmap.option.MapOptions;
import ru.oniip.tetraleafletmap.option.TileLayerOptions;

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
   * Инстанцирование layer group.
   *
   * @return Layer group.
   */
  LayerGroup layerGroup();
}
