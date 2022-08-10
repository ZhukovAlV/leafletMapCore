package tetramap.event;

import tetramap.entity.LatLong;

public interface MapMoveEventListener {
    void mouseMoved(LatLong latLong);
}
