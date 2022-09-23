package tetramap.entity;

import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

public class TileLayer extends LeafletObject {

    @Serial
    private static final long serialVersionUID = 2989693312308589756L;

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.tileLayer.toString();
    }
}
