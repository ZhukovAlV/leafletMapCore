package tetramap.entity.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.Icon;
import tetramap.entity.Point;
import tetramap.leaflet.LeafletObject;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class IconLeaflet extends LeafletObject implements Icon {

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

    public IconLeaflet(String iconUrl) {
        this.iconUrl = iconUrl;
        this.iconSize = DEFAULT_ICON_SIZE;
        this.iconAnchor = DEFAULT_ICON_ANCHOR;
    }

    public IconLeaflet(String iconUrl, Point iconSize, Point iconAnchor) {
        this.iconUrl = iconUrl;
        this.iconSize = iconSize;
        this.iconAnchor = iconAnchor;
    }

    @Override
    public String toString() {
        return "{iconUrl: '" + iconUrl +
                "', iconSize: " + iconSize +
                ", iconAnchor: " + iconAnchor + "}";
    }
}