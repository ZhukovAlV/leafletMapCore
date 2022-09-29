package tetramap.entity.vectors;

import tetramap.entity.types.LatLong;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

public class Circle extends CircleMarker {

    @Serial
    private static final long serialVersionUID = -9214599807982996954L;

    public Circle(LatLong center) {
        super(center);
    }

    public Circle(LatLong center, double radius) {
        super(center, radius);
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.circle.toString();
    }
}
