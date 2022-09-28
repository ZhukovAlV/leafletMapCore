package tetramap.layer;


import tetramap.gui.MapView;
import tetramap.leaflet.LeafletBasicType;

/**
 * Leaflet методы для Layer
 */
public interface Layer extends LeafletBasicType {

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
