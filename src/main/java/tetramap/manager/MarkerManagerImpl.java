package tetramap.manager;

import tetramap.entity.marker.Marker;
import tetramap.entity.types.Point;
import tetramap.entity.vectors.Circle;
import tetramap.entity.vectors.Polygon;
import tetramap.event.MarkerEventListener;
import tetramap.gui.MapView;
import tetramap.layer.Layer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MarkerManagerImpl implements MarkerManager {

    /**
     * Карта
     */
    private final MapView mapView;

    /**
     * Подписчики на события выбора маркеров на карте
     */
    private final List<MarkerEventListener> markerEventListenerList = new CopyOnWriteArrayList<>();

    public MarkerManagerImpl(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public List<MarkerEventListener> getMarkers() {
        return markerEventListenerList;
    }

    @Override
    public void addMarker(MarkerEventListener markerEventListener) {
        markerEventListenerList.add(markerEventListener);
    }

    @Override
    public void removeMarker(MarkerEventListener markerEventListener) {
        markerEventListenerList.remove(markerEventListener);
    }

    @Override
    public void clearMarkers() {
        markerEventListenerList.clear();
    }

    @Override
    public void notifyMarkerSelection(Marker marker, boolean selectionState) {
        for (MarkerEventListener listener : markerEventListenerList) {
            listener.onMarkerSelected(marker, selectionState);
        }
    }

    @Override
    public void notifyMarkersSelection(List<Marker> markers, boolean selectionState) {
        for (MarkerEventListener listener : markerEventListenerList) {
            listener.onMarkersSelected(markers, selectionState);
        }
    }

    /**
     * Выбирает все маркеры по указанному слою (поддерживаются полигоны и круги)
     * @param layer Layer
     */
    public void selectMarkersInLayer(Layer layer) {

        if (layer instanceof Polygon) {
            selectMarkersInPolygon((Polygon) layer);

        } else if (layer instanceof Circle) {
            selectMarkersInCircle((Circle) layer);
        }
    }

    /**
     * Выбирает все маркеры в указанном полигоне
     * Маркеры, попавшие в область полигона - выбираются
     * Остальные становятся невыбранными
     * @param polygon Polygon
     * @return возвращает маркеры в заданной области
     */
    public List<Marker> selectMarkersInPolygon(Polygon polygon) {

/*        List<Marker> selectedMarkers = new ArrayList<>();
        List<Marker> unselectedMarkers = new ArrayList<>();
        for (SubscriberMarker marker : markers) {

            // Отсеиваем невидимые маркеры и маркеры без позиции
            if (marker.getLatLong() == null) continue;

            // Определяем, попал ли маркер в область
            boolean selected = LatLongUtils.contains(polygon.getLatLongs(), marker.getLatLong());
            if (selected) selectedMarkers.add(marker);
            else unselectedMarkers.add(marker);
            // Изменяем состояние выбора для маркера
            marker.setSelected(selected);
        }

        // Убираем выделенные маркеры из кластера
        removeMarkersFromCluster(selectedMarkers);

        // Уведомление подписчиков об изменении состояния маркеров
        if (!selectedMarkers.isEmpty()) notifyMarkerSelection(selectedMarkers, true);
        if (!unselectedMarkers.isEmpty()) notifyMarkerSelection(unselectedMarkers, false);
        mapView.getLayerManager().redrawLayers();

        return selectedMarkers;*/

        return null;
    }

    /**
     * Выбирает все маркеры в указанном полигоне
     * Маркеры, попавшие в область полигона - НЕ выбираются
     * @param polygon Polygon
     * @return возвращает маркеры в заданной области
     */
    public List<Marker> selectMarkersInPolygonWithoutSelected(Polygon polygon) {
        // TODO доделать
        return null;
    }

    /**
     * Выбирает все маркеры в указанном круге (остальные становятся невыбранными)
     * @param circle круг, по которому осуществляется выделение маркеров
     */
    public void selectMarkersInCircle(Circle circle) {
        // TODO доделать
    }

    /**
     * Выделяет (снимает выделение) все маркеры на карте
     * @param selected состояние выделения
     */
    public void selectAll(boolean selected) {
        // TODO доделать
    }

    /**
     * Возвращает пиксельную точку местоположения для переданного маркера
     * @param marker маркер, для которого определяется точка
     * @return Point
     */
    public Point getMarkerPoint(Marker marker) {
        // TODO доделать
        return null;
    }
}
