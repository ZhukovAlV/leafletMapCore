package tetramap.config;

import lombok.Getter;
import tetramap.leaflet.LeafletMap;
import tetramap.entity.TileLayer;

import java.util.List;

@Getter
public class MapConfig {
    private final List<TileLayer> layers;
    private final ZoomControlConfig zoomControlConfig;
    private final ScaleControlConfig scaleControlConfig;
    private final LeafletMap map;

    public MapConfig(List<TileLayer> layers, ZoomControlConfig zoomControlConfig,
                     ScaleControlConfig scaleControlConfig, LeafletMap map) {
        this.layers = layers;
        this.zoomControlConfig = zoomControlConfig;
        this.scaleControlConfig = scaleControlConfig;
        this.map = map;
    }
}
