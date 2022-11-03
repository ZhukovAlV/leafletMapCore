package tetramap.entity.control;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.leaflet.LeafletControl;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class ZoomControl extends LeafletControl {

    @Serial
    private static final long serialVersionUID = -1579783907685084701L;

    private final boolean show;
    private final LeafletControl.ControlPosition position;

    @Override
    public String toString() {
        return String.join("", "{position:'", position.toString(), "'}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.ZOOM.getName();
    }
}
