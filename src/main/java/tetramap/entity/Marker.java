package tetramap.entity;

public interface Marker {

    /**
     * Устанавка координат маркера
     *
     * @param latLong координаты маркера
     */
    void setLatLong(LatLong latLong);

    /**
     * Получение координат маркера
     *
     * @return координаты маркера
     */
    LatLong getLatLong();

    /**
     * Устанавка иконки маркера
     *
     * @param icon иконка маркера
     */
    void setIcon(Icon icon);

    /**
     * Получение иконки маркера
     *
     * @return иконка маркера
     */
    Icon getIcon();
}
