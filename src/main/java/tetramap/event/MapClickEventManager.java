package tetramap.event;

import tetramap.entity.LatLong;

import java.util.ArrayList;

public class MapClickEventManager {
    private final ArrayList<MapClickEventListener> listeners = new ArrayList<>();

    public void addListener(MapClickEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void removeListener(MapClickEventListener toRemove) {
        listeners.remove(toRemove);
    }

    public void mapClickEvent(LatLong latLong) {
        for (MapClickEventListener mapClickEventListener : listeners) {
            mapClickEventListener.mouseClicked(latLong);
        }
    }
}
