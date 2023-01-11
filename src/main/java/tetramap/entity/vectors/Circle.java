package tetramap.entity.vectors;

import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
public class Circle extends CircleMarker {

    private static final long serialVersionUID = -9214599807982996954L;

    public Circle(LatLong center) {
        super(center);
    }

    public Circle(LatLong center, double radius) {
        super(center, radius);
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.CIRCLE.getName();
    }
}
