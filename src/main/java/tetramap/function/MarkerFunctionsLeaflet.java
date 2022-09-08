package tetramap.function;

import tetramap.entity.Marker;
import tetramap.gui.MapViewLeaflet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MarkerFunctionsLeaflet {

    // Карта
    private final MapViewLeaflet mapView;

    // Подписчики на события выбора маркеров на карте
    private final List<Marker> markerList = new CopyOnWriteArrayList<>();

    public MarkerFunctionsLeaflet(MapViewLeaflet mapView) {
        this.mapView = mapView;
    }

    public void addMarker(Marker marker) {
        markerList.add(marker);
    }

    public void removeMarker(Marker marker) {
        markerList.remove(marker);
    }
}
