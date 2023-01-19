package tetramap.manager;

import tetramap.entity.types.LatLong;
import tetramap.event.MapMoveEventListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapMoveEventManagerImpl implements MapMoveEventManager {
    private final List<MapMoveEventListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addListener(MapMoveEventListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void removeListener(MapMoveEventListener toRemove) {
        listeners.remove(toRemove);
    }

    @Override
    public void mapMoveEvent(LatLong latLong) {
        for (MapMoveEventListener mapMoveEventListener : listeners) {
            mapMoveEventListener.mouseMoved(latLong);
        }
    }
}
