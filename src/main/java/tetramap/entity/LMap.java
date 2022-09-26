package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.control.LAttributionControl;
import tetramap.gui.ViewContainer;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
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

    public void createTo(ViewContainer viewContainer) {
        viewContainer.execScript("var " + this.getId() + " = L." + this.getTypeInstantiatesMap() + this + ";");
        viewContainer.execScript(this.getId() + "." + lAttributionControl.getTypeInstantiatesMap()
                + ".setPrefix(" + lAttributionControl.getPrefix() + ");");
    }
}
