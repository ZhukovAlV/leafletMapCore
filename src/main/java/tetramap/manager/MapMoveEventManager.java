package tetramap.manager;

import tetramap.entity.types.LatLong;
import tetramap.event.MapMoveEventListener;

public interface MapMoveEventManager {

    void addListener(MapMoveEventListener toAdd);
    void removeListener(MapMoveEventListener toRemove);
    void mapMoveEvent(LatLong latLong);
}