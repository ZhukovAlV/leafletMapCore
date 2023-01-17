package tetramap.config;

import lombok.Getter;
import tetramap.entity.TileLayer;
import tetramap.entity.control.ScaleControl;
import tetramap.entity.control.ZoomControl;
import tetramap.entity.types.LatLongBounds;
import tetramap.leaflet.LeafletMap;

import java.util.List;

@Getter
public class MapConfig {
    private final List<TileLayer> layers;
    private final ZoomControl zoomControl;
    private final ScaleControl scaleControl;
    private final LeafletMap map;
    private final LatLongBounds latLongBounds;

    public MapConfig(List<TileLayer> layers, ZoomControl zoomControl,
                     ScaleControl scaleControl, LeafletMap map, LatLongBounds latLongBounds) {
        this.layers = layers;
        this.zoomControl = zoomControl;
        this.scaleControl = scaleControl;
        this.map = map;
        this.latLongBounds = latLongBounds;
    }
}
