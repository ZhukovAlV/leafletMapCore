package tetramap.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.Icon;
import tetramap.entity.LatLong;
import tetramap.entity.Marker;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MarkerLeaflet extends LeafletObject implements Marker {

    @Serial
    private static final long serialVersionUID = 5997712572773708479L;

    /**
     * Координаты маркера
     */
    private LatLong latLong;

    /**
     * Иконка маркера
     */
    private Icon icon;

    @Override
    public String toString() {
        return latLong + ", {icon: " + icon.getId() + '}';
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.marker.toString();
    }
}