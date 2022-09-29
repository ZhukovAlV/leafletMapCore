package tetramap.entity.vectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.LatLong;
import tetramap.entity.LatLongBounds;
import tetramap.entity.vectors.structure.GeometryStructure;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Polyline extends Path {

    @Serial
    private static final long serialVersionUID = -2430760430165501877L;
    
    private double smoothFactor = 1.0;
    private boolean noClip;
    private final LatLongArray latLongs;

    public Polyline(LatLong... latLongs) {
        this(new LatLongArray(latLongs));
    }

    public Polyline(List<LatLong> latLongs) {
        this(new LatLongArray(latLongs));
    }

    public Polyline(LatLongArray latLongs) {
        this.latLongs = latLongs;
    }

    @Override
    public String toString() {
        return "(" + latLongs + ", {color: '" + getColor() + "'})";
    }

    /**
     * Returns true if the Polyline has no LatLongs.
     *
     * @return true if it has no coordinates
     */
    public boolean isEmpty() {
        return getLatLongs().isEmpty();
    }

    /**
     * Returns the LatLongBounds of the path.
     *
     * @return the bounds of the polyline
     */
    public LatLongBounds getBounds() {
        return getLatLongs().getBounds();
    }

    public GeometryStructure getLatLongs() {
        return latLongs;
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.polyline.toString();
    }
}
