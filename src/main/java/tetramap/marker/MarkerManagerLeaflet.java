package tetramap.marker;

import tetramap.entity.LatLong;
import tetramap.entity.Marker;
import tetramap.gui.MapViewLeaflet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MarkerManagerLeaflet implements MarkerManager {

    // Карта
    private final MapViewLeaflet mapView;

    // Подписчики на события выбора маркеров на карте
    private final List<Marker> markerList = new CopyOnWriteArrayList<>();

    public MarkerManagerLeaflet(MapViewLeaflet mapView) {
        this.mapView = mapView;
    }

    /**
     * Добавляет маркер в список
     * @param marker маркер
     */
    public void addMarker(Marker marker) {
        markerList.add(marker);
    }

    /**
     * Удаляет маркер из списка
     * @param marker маркер
     */
    public void removeMarker(Marker marker) {
        markerList.remove(marker);
    }
}
