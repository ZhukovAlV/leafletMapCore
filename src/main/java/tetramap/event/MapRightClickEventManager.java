package tetramap.event;

import java.util.concurrent.CopyOnWriteArrayList;

public class MapRightClickEventManager {

    private final CopyOnWriteArrayList<MapRightClickEventListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(MapRightClickEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void removeListener(MapRightClickEventListener toRemove) {
        listeners.remove(toRemove);
    }

    public void mapRightClickEvent() {
        for (MapRightClickEventListener mapRightClickEventListener : listeners) {
            mapRightClickEventListener.rightMouseClicked();
        }
    }
}
