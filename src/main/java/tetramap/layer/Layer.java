package tetramap.layer;


import tetramap.entity.BasicType;
import tetramap.gui.MapView;

/**
 * Leaflet методы для слоев
 */
public interface Layer extends BasicType {

    /**
     * Добавления слоя на карту
     * @param mapView карта
     */
    default void addTo(MapView mapView) {
        mapView.addLayer(this);
    }

    // removeFrom(<Map> map)
}
