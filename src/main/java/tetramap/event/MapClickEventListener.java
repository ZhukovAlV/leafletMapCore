package tetramap.event;

import tetramap.entity.types.LatLong;

public interface MapClickEventListener {
    void mouseClicked(LatLong latLong);
}
