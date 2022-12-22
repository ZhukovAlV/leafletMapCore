package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TileLayer extends LeafletObject {

    private static final long serialVersionUID = 2989693312308589756L;

    private String displayName;
    private String url;
    private String attribution;
    private boolean isElliptical;
    private String subdomains;
    private int minZoom;
    private int maxZoom;

    @Override
    public String toString() {
        return String.join("","'", url, "', ",
                "{attribution: '", attribution, "'",
                ", isElliptical: " + isElliptical,
                ", subdomains: '", subdomains, "'",
                ", minZoom: " + minZoom,
                ", maxZoom: " + maxZoom,
                "}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.TILE_LAYER.getName();
    }
}
