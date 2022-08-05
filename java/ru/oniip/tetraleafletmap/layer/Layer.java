package ru.oniip.tetraleafletmap.layer;

import ru.oniip.tetraleafletmap.draw.PopupSupport;
import ru.oniip.tetraleafletmap.draw.TooltipSupport;
import ru.oniip.tetraleafletmap.event.Evented;
import ru.oniip.tetraleafletmap.gui.LeafletMap;

/**
 * Layer (слой)
 */
public interface Layer extends Evented, PopupSupport, TooltipSupport {

  /**
   * Добавление Layer в карту
   *
   * @param map Map.
   */
  void addTo(LeafletMap map);

  /**
   * Добавление Layer в layer group.
   *
   * @param layerGroup Layer group.
   */
  void addTo(LayerGroup layerGroup);

  /**
   * Удаление текущего активного Layer с карты
   */
  void remove();

  /**
   * Удаление выбранного Layer с карты
   *
   * @param map Map.
   */
  void removeFrom(LeafletMap map);

  /**
   * Удаление выбранного Layer из layer group.
   *
   * @param layerGroup Layer group.
   */
  void removeFrom(LayerGroup layerGroup);
}
