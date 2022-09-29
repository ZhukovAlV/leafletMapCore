package tetramap.entity.vectors;

import tetramap.entity.LatLong;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.List;

public class Rectangle extends Polygon {

    @Serial
    private static final long serialVersionUID = -1163695901226058734L;

    public Rectangle(List<LatLong> listLatlong) {
        super(listLatlong);
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.rectangle.toString();
    }
}
