package tetramap.event;

import tetramap.entity.LatLong;
import tetramap.gui.MapView;

import java.util.ArrayList;
import java.util.Iterator;

public class MapMoveEventManager {
    private final ArrayList<MapMoveEventListener> listeners = new ArrayList<>();

    public void addListener(MapMoveEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void mapMoveEvent(LatLong latLong, MapView map) {
        Iterator iterator = listeners.iterator();
        while(iterator.hasNext()) {
            MapMoveEventListener mapMoveEventListener = (MapMoveEventListener)iterator.next();
            mapMoveEventListener.mouseMoved(latLong, map);
        }
    }
}
