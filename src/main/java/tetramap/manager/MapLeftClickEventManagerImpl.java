package tetramap.manager;

import tetramap.entity.types.LatLong;
import tetramap.event.MapLeftClickEventListener;

import java.util.concurrent.CopyOnWriteArrayList;

public class MapLeftClickEventManagerImpl implements MapLeftClickEventManager {
    private final CopyOnWriteArrayList<MapLeftClickEventListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addListener(MapLeftClickEventListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void removeListener(MapLeftClickEventListener toRemove) {
        listeners.remove(toRemove);
    }

    @Override
    public void mapLeftClickEvent(LatLong latLong) {
        for (MapLeftClickEventListener mapLeftClickEventListener : listeners) {
            mapLeftClickEventListener.leftMouseClicked(latLong);
        }
    }
}
