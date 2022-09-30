package tetramap.layer;


import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;

/**
 * Leaflet методы для Layer
 */
public abstract class Layer extends LeafletObject {

    /**
     * Добавления слоя на карту
     */
    public void addTo(MapView mapView) {
        super.addTo(mapView);
        getMapView().addLayer(this);
    }

    /**
     * Удаление слоя с карты
     */
    public void remove(){
        getMapView().removeLayer(this);
    }
}
