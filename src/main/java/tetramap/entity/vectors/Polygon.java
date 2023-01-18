package tetramap.entity.vectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.type.TypeInstantiatesMap;

import java.util.List;

/**
 * A class for drawing polygon overlays on a map. Extends Polyline. Note that
 * points you pass when creating a polygon shouldn't have an additional last
 * point equal to the first one — it's better to filter out such points.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Polygon extends Polyline {

    private static final long serialVersionUID = -128072866378031092L;

    public Polygon(List<LatLong> latLongs) {
        super(latLongs);
    }

    public Polygon(LatLong... latLongs) {
        super(latLongs);
    }

    public Polygon(LatLongArray latLongs) {
        super(latLongs);
    }

    @Override
    public String toString() {
        return getLatLongs() + ", {" + getPathOptions().toString() + "}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.POLYGON.getName();
    }
}
