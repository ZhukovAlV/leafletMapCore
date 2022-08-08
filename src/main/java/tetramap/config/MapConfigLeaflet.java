package tetramap.config;

import tetramap.entity.LatLng;
import tetramap.layer.MapLayer;

import java.util.List;

public class MapConfigLeaflet implements MapConfig {
    private final List<MapLayer> layers;
    private final ZoomControlConfig zoomControlConfig;
    private final ScaleControlConfig scaleControlConfig;
    private final LatLng initialCenter;

    public MapConfigLeaflet(List<MapLayer> layers, ZoomControlConfig zoomControlConfig, ScaleControlConfig scaleControlConfig, LatLng initialCenter) {
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
    public LatLng getInitialCenter() {
        return initialCenter;
    }
}
