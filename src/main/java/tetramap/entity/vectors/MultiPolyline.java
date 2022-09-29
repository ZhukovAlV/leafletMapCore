package tetramap.entity.vectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.vectors.structure.GeometryStructure;
import tetramap.entity.vectors.structure.MultiLatLongArray;

import java.io.Serial;

/**
 * A class for drawing multi polyline overlays on a map.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MultiPolyline extends Polyline {

    @Serial
    private static final long serialVersionUID = 1274901686872790896L;

    private final MultiLatLongArray latLongs;

    @Override
    public GeometryStructure getLatLongs() {
        return this.latLongs;
    }
}
