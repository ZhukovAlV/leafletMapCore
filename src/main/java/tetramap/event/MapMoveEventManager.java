package tetramap.event;

import tetramap.entity.LatLong;

import java.util.ArrayList;

public class MapMoveEventManager {
    private final ArrayList<MapMoveEventListener> listeners = new ArrayList<>();

    public void addListener(MapMoveEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void mapMoveEvent(LatLong latLong) {
        for (MapMoveEventListener mapMoveEventListener : listeners) {
            mapMoveEventListener.mouseMoved(latLong);
        }
    }
}
