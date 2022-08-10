package tetramap.event;

import tetramap.entity.LatLong;

public interface MapClickEventListener {
    void mouseClicked(LatLong latLong);
}
