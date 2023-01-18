package tetramap.entity.vectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.type.TypeInstantiatesMap;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Rectangle extends Polygon {

    private static final long serialVersionUID = -1163695901226058734L;

    public Rectangle(List<LatLong> latLongs) {
        super(latLongs);
    }

    public Rectangle(LatLong... latLongs) {
        super(latLongs);
    }

    public Rectangle(LatLongArray latLongs) {
        super(latLongs);
    }

    @Override
    public String toString() {
        return getLatLongs() + ", {" + getPathOptions().toString() + "}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.RECTANGLE.getName();
    }
}
