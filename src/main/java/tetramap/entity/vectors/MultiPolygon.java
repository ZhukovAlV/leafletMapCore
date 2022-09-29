package tetramap.entity.vectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.LatLongBounds;
import tetramap.entity.vectors.structure.GeometryStructure;
import tetramap.entity.vectors.structure.MultiLatLongArray;

import java.io.Serial;
import java.util.ArrayList;

/**
 * * A class for drawing multi polygon overlays on a map.
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MultiPolygon extends Polygon {

    @Serial
    private static final long serialVersionUID = 2133788010825025903L;

    private MultiPolygonStructure latlongs;

    @Override
    public String getLeafletType() {
        return Polygon.class.getSimpleName();
    }

    public static class MultiPolygonStructure extends ArrayList<MultiLatLongArray> implements GeometryStructure {

        @Serial
        private static final long serialVersionUID = -8597806785233271725L;

        @Override
        public LatLongBounds getBounds() {
            LatLongBounds bounds = new LatLongBounds();
            forEach((latlong) -> bounds.extend(latlong.getBounds()));
            return bounds;
        }
    }
}
