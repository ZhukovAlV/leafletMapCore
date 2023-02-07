package tetramap.manager;

import tetramap.event.MapPopupEventListener;

import java.util.concurrent.CopyOnWriteArrayList;

public class MapPopupEventManagerImpl implements MapPopupEventManager {

    private final CopyOnWriteArrayList<MapPopupEventListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addListener(MapPopupEventListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void removeListener(MapPopupEventListener toRemove) {
        listeners.remove(toRemove);
    }

    @Override
    public void mapPopupOpenEvent() {
        for (MapPopupEventListener mapPopupEventListener : listeners) {
            mapPopupEventListener.popupOpen();
        }
    }

}