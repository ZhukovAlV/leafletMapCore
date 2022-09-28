package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Polyline extends Path {

    @Serial
    private static final long serialVersionUID = -2430760430165501877L;

    private final List<LatLong> listLatlong;

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.polyline.toString();
    }
}
