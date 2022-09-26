package tetramap.config;

import lombok.Getter;
import tetramap.entity.LMap;
import tetramap.entity.LTileLayer;

import java.util.List;

@Getter
public class MapConfig {
    private final List<LTileLayer> layers;
    private final ZoomControlConfig zoomControlConfig;
    private final ScaleControlConfig scaleControlConfig;
    private final LMap map;

    public MapConfig(List<LTileLayer> layers, ZoomControlConfig zoomControlConfig,
                     ScaleControlConfig scaleControlConfig, LMap map) {
        this.layers = layers;
        this.zoomControlConfig = zoomControlConfig;
        this.scaleControlConfig = scaleControlConfig;
        this.map = map;
    }
}
