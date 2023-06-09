package tetramap.entity.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

/**
 * Иконка для маркера Leaflet
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Icon extends LeafletObject implements BasicType {

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

    public Icon(String iconUrl) {
        this.iconUrl = iconUrl;
        this.iconSize = DEFAULT_ICON_SIZE;
        this.iconAnchor = DEFAULT_ICON_ANCHOR;
    }

    public Icon(String iconUrl, Point iconSize) {
        this.iconUrl = iconUrl;
        this.iconSize = iconSize;
        this.iconAnchor = DEFAULT_ICON_ANCHOR;
    }

    public Icon(String iconUrl, double widthIcon, double heightIcon) {
        this(iconUrl, Point.of(widthIcon, heightIcon));
    }

    @Override
    public String toString() {
        return String.join("","{iconUrl: '", iconUrl,
                "', iconSize: ", iconSize.toString(),
                ", iconAnchor: ", iconAnchor.toString(), "}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.ICON.getName();
    }
}