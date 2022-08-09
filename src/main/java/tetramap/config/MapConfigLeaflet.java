package tetramap.config;

import tetramap.entity.LatLong;
import tetramap.layer.MapLayer;

import java.util.List;

public class MapConfigLeaflet implements MapConfig {
    private final List<MapLayer> layers;
    private final ZoomControlConfig zoomControlConfig;
    private final ScaleControlConfig scaleControlConfig;
    private final LatLong initialCenter;

    public MapConfigLeaflet(List<MapLayer> layers, ZoomControlConfig zoomControlConfig, ScaleControlConfig scaleControlConfig, LatLong initialCenter) {
        this.layers = layers;
        this.zoomControlConfig = zoomControlConfig;
        this.scaleControlConfig = scaleControlConfig;
        this.initialCenter = initialCenter;
    }

    @Override
    public List<MapLayer> getLayers() {
        return layers;
    }

    @Override
    public ZoomControlConfig getZoomControlConfig() {
        return zoomControlConfig;
    }

    @Override
    public ScaleControlConfig getScaleControlConfig() {
        return scaleControlConfig;
    }

    @Override
    public LatLong getInitialCenter() {
        return initialCenter;
    }
}
