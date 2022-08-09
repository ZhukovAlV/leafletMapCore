package tetramap.event;

import tetramap.entity.LatLong;

public interface MapMoveEventListener {
    void onMapMove(LatLong latLong);
}
