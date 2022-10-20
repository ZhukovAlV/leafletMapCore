package tetramap.layer;

import lombok.Data;

import tetramap.entity.popup.Popup;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;

/**
 * Leaflet методы для Layer
 */
@Data
public abstract class Layer extends LeafletObject {

    public static final String DEFAULT_PANE = "overlayPane";
    private String pane = DEFAULT_PANE;
    private Popup popup;

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

    /**
     * Popup (всплывающее сообщение) для объекта
     */
    public void bindPopup(){
        getMapView().bindPopup(this);
    }
}
