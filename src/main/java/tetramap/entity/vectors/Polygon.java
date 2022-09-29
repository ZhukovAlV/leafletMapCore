package tetramap.entity.vectors;

import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.structure.GeometryStructure;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.entity.vectors.structure.MultiLatLongArray;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

/**
 * A class for drawing polygon overlays on a map. Extends Polyline. Note that
 * points you pass when creating a polygon shouldn't have an additional last
 * point equal to the first one — it's better to filter out such points.
 */
public class Polygon extends Polyline {

    @Serial
    private static final long serialVersionUID = -128072866378031092L;
    
    private final MultiLatLongArray latLongs;

    public Polygon(LatLong... LatLongs) {
        this(Arrays.asList(LatLongs));
    }

    public Polygon(List<LatLong> exteriorLatLongs) {
        latLongs = new MultiLatLongArray();
        latLongs.add(new LatLongArray(exteriorLatLongs));
    }

    public Polygon(List<LatLong> exteriorLatLongs, MultiLatLongArray interiorRings) {
        latLongs = new MultiLatLongArray();
        latLongs.add(new LatLongArray(exteriorLatLongs));
        latLongs.addAll(interiorRings);
    }

    @Override
    public String toString() {
        return "(" + latLongs + ", {color: '" + getColor() + "'})";
    }

    @Override
    public GeometryStructure getLatLongs() {
        return this.latLongs;
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.polygon.toString();
    }
}
