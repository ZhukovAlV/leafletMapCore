package ru.oniip.tetraleafletmap.layer;

/**
 * Layer group. Используется для группировки нескольких слоев и обработки их как одного.
 */
public interface LayerGroup extends Layer {

  /**
   * Добавление layer в group.
   *
   * @param layer Layer.
   */
  void addLayer(Layer layer);

  /**
   * Удаление layer из group.
   *
   * @param layer Layer.
   */
  void removeLayer(Layer layer);

  /**
   * Проверка, добален ли layer в group
   *
   * @param layer Layer.
   * @return true если добавлен.
   */
  boolean hasLayer(Layer layer);

  /**
   * Удаление всех layers из group.
   */
  void clearLayers();

  /**
   * Вызывает setZIndex для каждого слоя, содержащегося в этой группе, передавая z-index.
   *
   * @param zIndex z-index.
   */
  //void setZIndex(Number zIndex);
}
