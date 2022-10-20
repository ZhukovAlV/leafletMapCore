package tetramap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.LatLong;
import tetramap.entity.types.Point;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class DivOverlay extends Layer {

    /**
     * Content of the overlay.
     */
    private String content;

    /**
     * A custom CSS class name to assign to the overlay.
     */
    private String className = "";

    /**
     * The offset of the overlay position. Useful to control the anchor
     * of the overlay when opening it on some overlays.
     */
    private Point offset = Point.of(0, 7);

    /**
     * Координаты окна сообщений
     */
    private LatLong latLong;

    protected DivOverlay(String content) {
        this.content = content;
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.divOverlay.toString();
    }
}
