package tetramap.entity;

import tetramap.layer.Layer;

/**
 * Leaflet маркер
 */
public interface Marker extends Layer {

    /**
     * Получение координат маркера
     *
     * @return координаты маркера
     */
    LatLong getLatLong();

    /**
     * Устанавка координат маркера
     *
     * @param latLong координаты маркера
     */
    void setLatLong(LatLong latLong);

    /**
     * Получение иконки маркера
     *
     * @return иконка маркера
     */
    Icon getIcon();

    /**
     * Устанавка иконки маркера
     *
     * @param icon иконка маркера
     */
    void setIcon(Icon icon);
}
