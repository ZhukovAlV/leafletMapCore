package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
@AllArgsConstructor
public class BaseMaps extends LeafletObject {

    private final List<TileLayer> tileLayers;

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
        return TypeInstantiatesMap.BASE_MAPS.getName();
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        mapView.execScript(String.join("", "var ", this.getId(), " = ", this.toString(), ";"));
    }
}
