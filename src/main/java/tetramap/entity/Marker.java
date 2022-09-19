package tetramap.entity;

public interface Marker {

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
