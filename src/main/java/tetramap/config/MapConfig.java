package tetramap.config;

import tetramap.entity.LatLng;
import tetramap.layer.MapLayer;

import java.util.List;

public interface MapConfig {
    List<MapLayer> getLayers();
    LatLng getInitialCenter();
    ScaleControlConfig getScaleControlConfig();
    ZoomControlConfig getZoomControlConfig();
}
