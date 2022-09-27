package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.control.LAttributionControl;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Log4j2
public class LMap extends LeafletObject {

    @Serial
    private static final long serialVersionUID = 3789693345308589828L;

    private String name;
    private LatLong center;
    private int zoom;
    private boolean zoomControl;
    private LTileLayer tileLayer;

    private LAttributionControl lAttributionControl;

    @Override
    public String toString() {
        return "(" + name +
                ", {center: " + center +
                ", zoom: " + zoom +
                ", zoomControl: " + zoomControl +
                ", layers: [" + tileLayer.getId() + "]})";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.map.toString();
    }

    public void createTo(MapView mapView) {
        log.info("Создание карты LMap: {}", "id: " + this.getId());
        mapView.execScript("var " + this.getId() + " = L." + this.getTypeInstantiatesMap() + this + ";");

        log.info("Установка аттрибутов для карты LMap: {}", "id: " + this.getId());
        lAttributionControl.addTo(mapView);
        lAttributionControl.setPrefix(mapView);
    }
}
