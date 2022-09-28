package tetramap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class CircleMarker extends Path {

    @Serial
    private static final long serialVersionUID = 8294051897899342825L;

    private final LatLong center;
    private final double radius;

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.circleMarker.toString();
    }
}
