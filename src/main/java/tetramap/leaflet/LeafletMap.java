package tetramap.leaflet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.LatLong;
import tetramap.entity.TileLayer;
import tetramap.entity.control.AttributionControl;
import tetramap.gui.MapView;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Log4j2
public class LeafletMap extends LeafletObject {

    @Serial
    private static final long serialVersionUID = 3789693345308589828L;

    private String name;
    private LatLong center;
    private int zoom;
    private boolean zoomControl;
    private TileLayer tileLayer;

    private AttributionControl attributionControl;

    @Override
    public String toString() {
        return String.join("","(", name,
                ", {center: ", center.toString(),
                ", zoom: " + zoom,
                ", zoomControl: " + zoomControl,
                ", layers: [", tileLayer.getId(), "]})");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.map.toString();
    }

    public void createTo(MapView mapView) {
        log.info("Создание карты LeafletMap: {}", "id: " + this.getId());
        mapView.execScript(String.join("","var ", this.getId(), " = L.", this.getTypeInstantiatesMap(), this.toString(), ";"));

        log.info("Установка аттрибутов для карты LeafletMap: {}", "id: " + this.getId());
        attributionControl.addTo(mapView);
        attributionControl.setPrefix(mapView);
    }
}
