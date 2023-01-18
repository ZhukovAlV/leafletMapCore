package tetramap.entity.vectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.type.TypeInstantiatesMap;

@Data
@EqualsAndHashCode(callSuper = true)
public class CircleMarker extends Path {

    private static final long serialVersionUID = 8294051897899342825L;

    private final LatLong center;
    private final double radius;

    /**
     * Конструктор с георграфическими координатами
     *
     * @param center географическая координата LatLong
     */
    public CircleMarker(LatLong center) {
        super();
        this.center = center;
        this.radius = 10;
    }

    public CircleMarker(LatLong center, PathOptions pathOptions) {
        this(center);
        setPathOptions(pathOptions);
    }

    public CircleMarker(LatLong center, double radius) {
        super();
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "[" + center.getLatitude() + ", " +
                center.getLongitude() + "], {"  + getPathOptions().toString() + ", radius: " +
                radius + "}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.CIRCLE_MARKER.getName();
    }
}
