package tetramap.entity.control;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.leaflet.LeafletControl;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScaleControl extends LeafletControl {

    private static final long serialVersionUID = -3415081890756100166L;

    private final boolean show;
    private final ControlPosition position;
    private final boolean metric;

    @Override
    public String toString() {
        return String.join("", "{position:'", position.toString(),
                "', metric: ", String.valueOf(metric), ", imperial: ", String.valueOf(!metric), "}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.SCALE.getName();
    }
}
