package tetramap.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.Icon;
import tetramap.entity.LatLong;
import tetramap.entity.Marker;
import tetramap.layer.Layer;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class MarkerLeaflet extends Layer implements Marker {

    private static final long serialVersionUID = 5997712572773708479L;

    /**
     * Координаты маркера
     */
    private LatLong latLong;

    /**
     * Иконка маркера
     */
    private Icon icon;
}
