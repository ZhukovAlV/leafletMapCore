package tetramap.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import tetramap.entity.Icon;
import tetramap.entity.LatLong;
import tetramap.entity.Marker;

@Data
@AllArgsConstructor
public class MarkerLeaflet implements Marker {

    /**
     * Координаты маркера
     */
    private LatLong latLong;

    /**
     * Иконка маркера
     */
    private Icon icon;
}
