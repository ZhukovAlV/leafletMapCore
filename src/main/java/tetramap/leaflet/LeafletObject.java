package tetramap.leaflet;

import lombok.Data;
import tetramap.entity.types.BasicType;
import tetramap.gui.MapView;

import java.util.UUID;

/**
 * Родительский класс объекта Leaflet
 */
@Data
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
}
