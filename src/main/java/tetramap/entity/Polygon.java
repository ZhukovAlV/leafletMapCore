package tetramap.entity;

import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.List;

public class Polygon extends Polyline {

    @Serial
    private static final long serialVersionUID = -128072866378031092L;

    public Polygon(List<LatLong> listLatlong) {
        super(listLatlong);
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.polygon.toString();
    }
}
