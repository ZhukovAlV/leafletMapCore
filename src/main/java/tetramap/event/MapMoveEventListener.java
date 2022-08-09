package tetramap.event;

import tetramap.entity.LatLong;
import tetramap.gui.MapView;

public interface MapMoveEventListener {
    void mouseMoved(LatLong latLong, MapView map);
}
