package tetramap.layer;


import tetramap.entity.BasicType;
import tetramap.gui.MapView;

/**
 * Leaflet методы для Layer
 */
public interface Layer extends BasicType {

    /**
     * Добавления слоя на карту
     *
     * @param mapView карта
     */
    default void addTo(MapView mapView) {
        mapView.addLayer(this);
    }

    /**
     * Удаление слоя с карты
     *
     * @param mapView карта
     */
    default void removeFrom(MapView mapView){
        mapView.removeLayer(this);
    }
}
