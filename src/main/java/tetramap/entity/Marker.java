package tetramap.entity;

public interface Marker {

    /**
     * Установить координаты маркера
     *
     * @param latLong координаты маркера
     */
    void setLatLong(LatLong latLong);

    /**
     * Получить координаты маркера
     *
     * @return координаты маркера
     */
    LatLong getLatLong();

    /**
     * Установить иконку маркера
     * @param icon иконка маркера
     */
    void setIcon(Icon icon);

    /**
     * Получить иконку маркера
     * @return иконка маркера
     */
    Icon getIcon();
}
