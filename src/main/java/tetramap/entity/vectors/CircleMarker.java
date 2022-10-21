package tetramap.entity.vectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CircleMarker extends Path {

    @Serial
    private static final long serialVersionUID = 8294051897899342825L;

    private final LatLong center;
    private final double radius;

    /**
     * Instantiates a circle marker object given a geographical point
     *
     * @param center geographical point
     */
    public CircleMarker(LatLong center) {
        this(center, 10);
    }

    @Override
    public String toString() {
        return "[" + center.getLatitude() + "," +
                center.getLongitude() + "], {radius: " +
                radius + "}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.circleMarker.toString();
    }
}
