package ru.oniip.tetraleafletmap.layer;

/**
 * Тайловый слой.
 */
public interface TileLayer extends GridLayer {

  /**
   * Обновление layer's URL и перерисовка.
   *
   * @param url URL template.
   */
  void setUrl(String url);

  /**
   * Обновление layer's URL и перерисовка (если noRedraw выставлена true).
   *
   * @param url      URL template.
   * @param noRedraw true to skip redraw.
   */
  void setUrl(String url, boolean noRedraw);
}
