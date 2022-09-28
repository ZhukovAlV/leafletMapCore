package tetramap.entity.control;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.leaflet.LeafletControl;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScaleControl extends LeafletControl {

    @Serial
    private static final long serialVersionUID = -3415081890756100166L;

    private final boolean show;
    private final ControlPosition position;
    private final boolean metric;

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.scale.toString();
    }
}
