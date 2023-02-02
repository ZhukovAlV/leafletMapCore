package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

@Log4j2
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class TileLayer extends Layer {

    private static final long serialVersionUID = 2989693312308589756L;

    private String displayName;
    private String url;
    private String attribution;
    private boolean isElliptical;
    private String subdomains;
    private int minZoom;
    private int maxZoom;

    @Override
    public String toString() {
        return String.join("","'", url, "', ",
                "{attribution: '", attribution, "'",
                ", isElliptical: " + isElliptical,
                ", subdomains: '", subdomains, "'",
                ", minZoom: " + minZoom,
                ", maxZoom: " + maxZoom,
                "}");
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), "(", this.toString(), ");"));
    }

    @Override
    public void updateTo() {

        log.info("Удаление с карты TileLayer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");

        log.info("Обновляем значение TileLayer: {}", this.getId());
        getMapView().execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), "(", this.toString(), ");"));
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.TILE_LAYER.getName();
    }

}
