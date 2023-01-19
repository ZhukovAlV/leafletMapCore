package tetramap.manager;

import tetramap.event.MapRightClickEventListener;

public interface MapRightClickEventManager {

    void addListener(MapRightClickEventListener toAdd);
    void removeListener(MapRightClickEventListener toRemove);
    void mapRightClickEvent();
}