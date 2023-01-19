package tetramap.event;

import tetramap.entity.types.LatLong;

import java.util.concurrent.CopyOnWriteArrayList;

public class MapLeftClickEventListenerImpl {
    private final CopyOnWriteArrayList<MapLeftClickEventListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(MapLeftClickEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void removeListener(MapLeftClickEventListener toRemove) {
        listeners.remove(toRemove);
    }

    public void mapLeftClickEvent(LatLong latLong) {
        for (MapLeftClickEventListener mapLeftClickEventListener : listeners) {
            mapLeftClickEventListener.leftMouseClicked(latLong);
        }
    }
}
