package tetramap.config;

import org.mapsforge.core.model.LatLong;
import tetramap.layer.MapLayer;

import java.util.List;

public interface MapConfig {
    List<MapLayer> getLayers();
    LatLong getInitialCenter();
    ScaleControlConfig getScaleControlConfig();
    ZoomControlConfig getZoomControlConfig();
}
