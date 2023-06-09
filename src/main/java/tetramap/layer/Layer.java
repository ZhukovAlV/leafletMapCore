package tetramap.layer;

import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.popup.Popup;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;

/**
 * Leaflet методы для Layer
 */
@Log4j2
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Layer extends LeafletObject {

    public static final String DEFAULT_PANE = "overlayPane";
    private String pane = DEFAULT_PANE;
    private Popup popup;

    /**
     * Добавления слоя на карту
     */
    @Override
    public void createTo(MapView mapView) {
        super.createTo(mapView);
        getMapView().addLayer(this);
    }

    /**
     * Обновление слоя на карте
     */
    @Override
    public void updateTo() {
        super.updateTo();
        getMapView().updateLayer(this);
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
    public void bindPopup(Popup popup){
        this.popup = popup;

        log.info("Добавляется Popup к layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".bindPopup('" + popup.getContent() + "'," + popup + ");");
    }

}
