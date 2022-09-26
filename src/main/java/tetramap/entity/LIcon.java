package tetramap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.layer.Layer;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

/**
 * Иконка для маркера Leaflet
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LIcon extends LeafletObject implements BasicType {

    @Serial
    private static final long serialVersionUID = -5884170824139869060L;

    private static final Point DEFAULT_ICON_SIZE = Point.of(24, 24);
    private static final Point DEFAULT_ICON_ANCHOR = Point.of(12, 12);

    /**
     * Ссылка на иконку
     */
    private String iconUrl;

    /**
     * Размер иконки
     */
    private Point iconSize;

    private Point iconAnchor;

    public LIcon(String iconUrl) {
        this.iconUrl = iconUrl;
        this.iconSize = DEFAULT_ICON_SIZE;
        this.iconAnchor = DEFAULT_ICON_ANCHOR;
    }

    public LIcon(String iconUrl, Point iconSize, Point iconAnchor) {
        this.iconUrl = iconUrl;
        this.iconSize = iconSize;
        this.iconAnchor = iconAnchor;
    }

    @Override
    public String toString() {
        return "({iconUrl: '" + iconUrl +
                "', iconSize: " + iconSize +
                ", iconAnchor: " + iconAnchor + "})";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.icon.toString();
    }
}