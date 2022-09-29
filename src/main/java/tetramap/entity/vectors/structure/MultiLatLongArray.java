package tetramap.entity.vectors.structure;

import lombok.NoArgsConstructor;
import tetramap.entity.types.LatLongBounds;

import java.io.Serial;
import java.util.ArrayList;

/**
 * Represents multi dimensional list of LatLng coordinates
 */
@NoArgsConstructor
public class MultiLatLongArray extends ArrayList<LatLongArray> implements GeometryStructure {

    @Serial
    private static final long serialVersionUID = 7453345091992067182L;

    @Override
    public LatLongBounds getBounds() {
        LatLongBounds bounds = new LatLongBounds(get(0));
        forEach(bounds::extend);
        return bounds;
    }
}