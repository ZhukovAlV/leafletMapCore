package tetramap.entity.control;

import lombok.Getter;
import lombok.Setter;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

/**
 * Устанавливает префикс (название слоя карты)
 */
@Getter
@Setter
public class LAttributionControl extends LeafletObject {

    @Serial
    private static final long serialVersionUID = 9098765502642340035L;

    private String prefix = "";

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.attributionControl.toString();
    }
}

