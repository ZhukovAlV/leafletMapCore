package tetramap.config;

import tetramap.entity.LatLong;
import tetramap.layer.MapLayerEnum;

import java.util.List;

public class MapConfig {
    private final List<MapLayerEnum> layers;
    private final ZoomControlConfig zoomControlConfig;
    private final ScaleControlConfig scaleControlConfig;
    private final LatLong initialCenter;

    public MapConfig(List<MapLayerEnum> layers, ZoomControlConfig zoomControlConfig, ScaleControlConfig scaleControlConfig, LatLong initialCenter) {
        this.layers = layers;
        this.zoomControlConfig = zoomControlConfig;
        this.scaleControlConfig = scaleControlConfig;
        this.initialCenter = initialCenter;
    }

    public List<MapLayerEnum> getLayers() {
        return layers;
    }

    public ZoomControlConfig getZoomControlConfig() {
        return zoomControlConfig;
    }

    public ScaleControlConfig getScaleControlConfig() {
        return scaleControlConfig;
    }

    public LatLong getInitialCenter() {
        return initialCenter;
    }
}
