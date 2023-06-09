package tetramap.leaflet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.Attribution;
import tetramap.entity.TileLayer;
import tetramap.entity.types.LatLong;
import tetramap.gui.MapView;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Log4j2
public class LeafletMap extends LeafletObject {

    private static final long serialVersionUID = 3789693345308589828L;

    private String name;

    private LatLong center;
    private int zoom;
    private int minZoom;
    private int maxZoom;
    private boolean zoomControl;
    private TileLayer tileLayer;

    private Attribution attribution;

    @Override
    public String toString() {
        return String.join("",name,
                ", {center: ", center.toString(),
                ", renderer: L.canvas()",
                ", zoom: " + zoom,
                ", zoomControl: " + zoomControl,
                ", layers: [", tileLayer.getId(), "]}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.MAP.getName();
    }

    public void createTo(MapView mapView) {
        setMapView(mapView);

        log.info("Создание карты LeafletMap, id: {}", this.getId());
        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),"(", this.toString(), ");"));

        log.info("Установка атрибутов для карты LeafletMap.");
        attribution.createTo(mapView);
        attribution.setPrefix();
    }

}
