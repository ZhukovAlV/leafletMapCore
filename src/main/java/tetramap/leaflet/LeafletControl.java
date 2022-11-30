package tetramap.leaflet;

import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@Log4j2
public abstract class LeafletControl extends LeafletObject {

    @Serial
    private static final long serialVersionUID = -9057192899900276429L;

    /**
     * Используется для расположения элементов
     */
    public enum ControlPosition {
        topleft, topright, bottomleft, bottomright
    }

    /**
     * Создание и добавление control во View карты
     *
     * @param mapView View карты
     */
    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        log.info("Создание ZoomControl, id: {}", this.getId());
        mapView.execScript(String.join("",this.getId(), " = L.",
                TypeInstantiatesMap.CONTROL.getName(), ".", this.getTypeInstantiatesMap(), "(",this.toString(), ");"));

        log.info("Добавление control: {}", this.getLeafletType() + ", id: " + this.getId());
        mapView.execScript(String.join("", this.getId(), ".addTo(", mapView.getMap().getId(), ");"));
    }

    /**
     * Удаление control
     */
    public void remove(MapView mapView) {
        log.info("Удаление control: {}", this.getLeafletType() + ", id: " + this.getId());
        mapView.execScript(this.getId() + ".remove()");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.CONTROL.getName();
    }
}
