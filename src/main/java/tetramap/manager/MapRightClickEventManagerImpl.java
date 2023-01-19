package tetramap.manager;

import tetramap.event.MapRightClickEventListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapRightClickEventManagerImpl implements MapRightClickEventManager {

    private final List<MapRightClickEventListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addListener(MapRightClickEventListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void removeListener(MapRightClickEventListener toRemove) {
        listeners.remove(toRemove);
    }

    @Override
    public void mapRightClickEvent() {
        for (MapRightClickEventListener mapRightClickEventListener : listeners) {
            mapRightClickEventListener.rightMouseClicked();
        }
    }
}
