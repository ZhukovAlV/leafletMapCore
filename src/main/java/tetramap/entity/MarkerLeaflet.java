package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import tetramap.function.MarkerFunctions;
import tetramap.layer.InteractiveLayer;

@Data
@AllArgsConstructor
public class MarkerLeaflet extends InteractiveLayer implements Marker, MarkerFunctions {

    /**
     * Координаты маркера
     */
    private LatLong latLong;

    /**
     * Иконка маркера
     */
    private Icon icon;
}
