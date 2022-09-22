package tetramap.layer;


import tetramap.entity.BasicType;
import tetramap.gui.LeafletMap;

/**
 * Leaflet методы для слоев
 */
public interface Layer extends BasicType {

    /**
     * Добавления слоя на карту
     * @param leafletMap карта
     */
    default void addTo(LeafletMap leafletMap) {
        leafletMap.addLayer(this);
    }
}
