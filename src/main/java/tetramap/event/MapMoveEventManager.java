package tetramap.event;

import tetramap.entity.types.LatLong;

import java.util.concurrent.CopyOnWriteArrayList;

public class MapMoveEventManager {
    private final CopyOnWriteArrayList<MapMoveEventListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(MapMoveEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void removeListener(MapMoveEventListener toRemove) {
        listeners.remove(toRemove);
    }

    public void mapMoveEvent(LatLong latLong) {
        for (MapMoveEventListener mapMoveEventListener : listeners) {
            mapMoveEventListener.mouseMoved(latLong);
        }
    }
}
