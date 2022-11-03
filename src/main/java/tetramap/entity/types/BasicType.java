package tetramap.entity.types;

import tetramap.gui.MapView;

import java.io.Serializable;

/**
 * Базовый тип для Leaflet объектов
 */
public interface BasicType extends Serializable {

    /**
     * Получение класса для Leaflet
     * @return тип класса объекта
     */
    default String getLeafletType() {
        return getClass().getSimpleName();
    }

    /**
     * Возвращает ID объекта
     * @return ID объекта
     */
    String getId();

    /**
     * Возвращает тип экземпляра
     * @return тип экземпляра
     */
    String getTypeInstantiatesMap();

    /**
     * Создание слоя во View карты
     * @param mapView View карты
     */
    void addTo(MapView mapView);

    /**
     * Обновление слоя во View карты
     */
    void updateTo();

    /**
     * Возвращает MapView, в котором был создан объект
     * @return MapView
     */
    MapView getMapView();
}