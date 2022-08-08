package tetramap.event;

import org.mapsforge.core.model.LatLong;

import java.util.ArrayList;
import java.util.Iterator;

public class MapClickEventManager {
    private final ArrayList<MapClickEventListener> listeners = new ArrayList<>();

    public void addListener(MapClickEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void mapClickEvent(LatLong latLong) {
        Iterator iterator = listeners.iterator();
        while(iterator.hasNext()) {
            MapClickEventListener mapClickEventListener = (MapClickEventListener)iterator.next();
            mapClickEventListener.onMapClick(latLong);
        }
    }
}
