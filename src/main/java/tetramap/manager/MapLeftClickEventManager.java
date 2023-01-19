package tetramap.manager;

import tetramap.entity.types.LatLong;
import tetramap.event.MapLeftClickEventListener;

public interface MapLeftClickEventManager {

    void addListener(MapLeftClickEventListener toAdd);
    void removeListener(MapLeftClickEventListener toRemove);
    void mapLeftClickEvent(LatLong latLong);
}