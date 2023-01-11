package tetramap.entity.vectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.type.TypeInstantiatesMap;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Rectangle extends Polygon {

    private static final long serialVersionUID = -1163695901226058734L;

    public Rectangle(List<LatLong> listLatlong) {
        super(listLatlong);
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.RECTANGLE.getName();
    }
}
