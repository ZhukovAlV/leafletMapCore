package tetramap.config;

import tetramap.entity.LatLong;
import tetramap.layer.MapLayer;

import java.util.List;

public interface MapConfig {
    List<MapLayer> getLayers();
    LatLong getInitialCenter();
    ScaleControlConfig getScaleControlConfig();
    ZoomControlConfig getZoomControlConfig();
}
