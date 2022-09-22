package tetramap.entity;

import tetramap.layer.Layer;

/**
 * Иконка для маркера Leaflet
 */
public interface Icon extends Layer {

    /**
     * Получение URL иконки
     *
     * @return URL иконки
     */
    String getIconUrl();

    /**
     * Устанавка URL иконки
     */
    void setIconUrl(String url);
}
