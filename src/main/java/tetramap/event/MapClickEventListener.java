package tetramap.event;

import tetramap.entity.LatLong;
import tetramap.gui.MapView;

public interface MapClickEventListener {
    void mouseClicked(LatLong latLong, MapView map);
}
