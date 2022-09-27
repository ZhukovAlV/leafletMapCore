package tetramap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class LBaseMaps extends LeafletObject {

    private final List<LTileLayer> tileLayers;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        tileLayers.forEach(lTileLayer -> stringBuilder.append("'")
                .append(lTileLayer.getDisplayName())
                .append("':")
                .append(lTileLayer.getId())
                .append(","));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.baseMaps.toString();
    }

    @Override
    public void createTo(MapView mapView) {
        mapView.execScript("var " + this.getId() + " = " + this + ";");
    }
}
