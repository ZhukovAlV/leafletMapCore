package tetramap.leaflet;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.types.BasicType;
import tetramap.gui.MapView;

import java.util.UUID;

/**
 * Родительский класс объекта Leaflet
 */
@Data
@Log4j2
public abstract class LeafletObject implements BasicType {

    /**
     * MapView в котором создан LeafletObject
     */
    private MapView mapView;

    /**
     * Генерируется уникальный ID
     */
    private final String ID = String.join("_", this.getLeafletType(), UUID.randomUUID().toString().replaceAll("-", "_"));

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void addTo(MapView mapView) {
        this.mapView = mapView;

        mapView.execScript(String.join("","var ", this.getId(), " = L.", this.getTypeInstantiatesMap(), "(", this.toString(), ");"));
    }

    @Override
    public void updateTo(MapView mapView) {
        this.mapView = mapView;

        log.info("Удаление с карты слоя layer: {}", this.getId());
        mapView.execScript(this.getId() + ".remove();");

        log.info("Обновляем значение layer: {}", this.getId());
        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), "(", this.toString(), ");"));
    }
}
