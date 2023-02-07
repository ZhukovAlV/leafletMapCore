package tetramap.manager;

import tetramap.event.MapPopupEventListener;

public interface MapPopupEventManager {

    void addListener(MapPopupEventListener toAdd);
    void removeListener(MapPopupEventListener toRemove);
    void mapPopupOpenEvent();

}