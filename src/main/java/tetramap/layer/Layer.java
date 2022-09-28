package tetramap.layer;


import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;

/**
 * Leaflet методы для Layer
 */
public abstract class Layer extends LeafletObject {

    /**
     * Добавления слоя на карту
     *
     * @param mapView карта
     */
    public void addTo(MapView mapView) {
        mapView.addLayer(this);
    }

    /**
     * Удаление слоя с карты
     *
     * @param mapView карта
     */
    public void removeFrom(MapView mapView){
        mapView.removeLayer(this);
    }
}
