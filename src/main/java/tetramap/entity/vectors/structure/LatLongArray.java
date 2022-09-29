package tetramap.entity.vectors.structure;

import tetramap.entity.types.LatLong;
import tetramap.entity.types.LatLongBounds;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents list of LatLng coordinates
 */
public class LatLongArray extends ArrayList<LatLong> implements GeometryStructure {

    @Serial
    private static final long serialVersionUID = -909287963967251959L;

    public LatLongArray(LatLong[] LatLongs) {
        this(Arrays.asList(LatLongs));
    }

    public LatLongArray(List<LatLong> LatLongs) {
        addAll(LatLongs);
    }

    @Override
    public LatLongBounds getBounds() {
        LatLongBounds bounds = new LatLongBounds(get(0));
        forEach(bounds::extend);
        return bounds;
    }
}
