package tetramap.entity;

public interface Circle {

    /**
     * Получение центра круга
     * @return координаты центра
     */
    LatLong getCenterPoint();

    /**
     * Установка центра круга
     * @param centerPoint координаты центра
     */
    void setCenterPoint(LatLong centerPoint);

    /**
     * Радиус
     * @return радиус
     */
    double getRadius();

    /**
     * Установка радиуса
     * @return радиус
     */
    void setRadius(double radius);
}
