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

    @Override
    public void createTo(MapView mapView) {
        log.info("Создание ZoomControl, id: {}", this.getId());
        mapView.execScript(String.join("","var ", this.getId(), " = L.",
                TypeInstantiatesMap.control.toString(), ".", this.getTypeInstantiatesMap(), this.toString(), ";"));
    }

    /**
     * Добавление control во View карты
     *
     * @param mapView View карты
     */
    public void addTo(MapView mapView){
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
        return TypeInstantiatesMap.control.toString();
    }
}
