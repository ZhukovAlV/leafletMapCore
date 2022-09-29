package tetramap.event;

import tetramap.entity.types.LatLong;

public interface MapMoveEventListener {
    void mouseMoved(LatLong latLong);
}
