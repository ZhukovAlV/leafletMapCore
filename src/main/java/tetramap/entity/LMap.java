package tetramap.entity;

import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

public class LMap extends LeafletObject {

    @Serial
    private static final long serialVersionUID = 3789693345308589828L;

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.map.toString();
    }
}
