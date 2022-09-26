package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.layer.Layer;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LMarker extends LeafletObject implements Layer {

    @Serial
    private static final long serialVersionUID = 5997712572773708479L;

    /**
     * Координаты маркера
     */
    private LatLong latLong;

    /**
     * Иконка маркера
     */
    private LIcon lIcon;

    @Override
    public String toString() {
        return "(" + latLong + ", {icon: " + lIcon.getId() + "})";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.marker.toString();
    }
}