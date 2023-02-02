package tetramap.manager;

import tetramap.entity.cluster.MarkerCluster;
import tetramap.entity.marker.Marker;
import tetramap.entity.marker.SubscriberMarker;
import tetramap.entity.types.Point;
import tetramap.entity.vectors.Circle;
import tetramap.entity.vectors.Polygon;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.event.MarkerEventListener;
import tetramap.gui.MapView;
import tetramap.layer.Layer;
import tetramap.util.LatLongUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MarkerManagerImpl implements MarkerManager {

    /**
     * Карта
     */
    private final MapView mapView;

    /**
     * Кластер для маркеров
     */
    private final MarkerCluster markerCluster;

    /**
     * Подписчики на события выбора маркеров на карте
     */
    private final List<SubscriberMarker> markers = new CopyOnWriteArrayList<>();

    public MarkerManagerImpl(MapView mapView) {
        this.mapView = mapView;

        markerCluster = new MarkerCluster();
        markerCluster.addTo(mapView);
    }

    @Override
    public List<SubscriberMarker> getMarkers() {
        return markers;
    }

    @Override
    public void addMarker(SubscriberMarker marker) {
        markers.add(marker);
        markerCluster.addLayer(marker);
    }

    @Override
    public void removeMarker(SubscriberMarker marker) {
        markers.remove(marker);
        markerCluster.removeLayer(marker);
    }

    @Override
    public void clearMarkers() {
        markers.clear();
        markerCluster.clearLayers();
    }

    @Override
    public void notifyMarkerSelection(SubscriberMarker marker, boolean selectionState) {
        marker.onMarkerSelected(selectionState);
    }

    @Override
    public void notifyMarkersSelection(List<SubscriberMarker> markers, boolean selectionState) {
        for (MarkerEventListener marker : markers) {
            marker.onMarkerSelected(selectionState);
        }
    }

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
    public List<SubscriberMarker> selectMarkersInPolygon(Polygon polygon) {

        List<SubscriberMarker> selectedMarkers = new ArrayList<>();
        List<SubscriberMarker> unselectedMarkers = new ArrayList<>();
        for (SubscriberMarker marker : markers) {
            // Определяем, попал ли маркер в область
            boolean selected = LatLongUtils.contains((LatLongArray)polygon.getLatLongs(), marker.getLatLong());

            if (selected) selectedMarkers.add(marker);
            else unselectedMarkers.add(marker);

            // Изменяем состояние выбора для маркера
            marker.setSelected(selected);
        }

        // Уведомление подписчиков об изменении состояния маркеров
        if (!selectedMarkers.isEmpty()) notifyMarkersSelection(selectedMarkers, true);
        if (!unselectedMarkers.isEmpty()) notifyMarkersSelection(unselectedMarkers, false);

        return selectedMarkers;
    }

    /**
     * Выбирает все маркеры в указанном полигоне
     * Маркеры, попавшие в область полигона - НЕ выбираются
     * @param polygon Polygon
     * @return возвращает маркеры в заданной области
     */
    public List<SubscriberMarker> selectMarkersInPolygonWithoutSelected(Polygon polygon) {

        List<SubscriberMarker> listMarkers = new ArrayList<>();

        for (SubscriberMarker marker : markers) {

            // Если маркер в полигоне, добавляем его
            if (LatLongUtils.contains((LatLongArray)polygon.getLatLongs(), marker.getLatLong())) listMarkers.add(marker);
        }

        return listMarkers;
    }

    /**
     * Выбирает все маркеры в указанном круге (остальные становятся невыбранными)
     * @param circle круг, по которому осуществляется выделение маркеров
     */
    public void selectMarkersInCircle(Circle circle) {

        List<SubscriberMarker> selectedMarkers = new ArrayList<>();
        List<SubscriberMarker> unselectedMarkers = new ArrayList<>();

        for (SubscriberMarker marker : markers) {

            // Определяем принадлежность местоположения маркера кругу
            boolean selected = LatLongUtils.sphericalDistance(circle.getCenter(), marker.getLatLong()) < circle.getRadius();

            if (selected) selectedMarkers.add(marker);
                else unselectedMarkers.add(marker);

            marker.setSelected(selected);
        }

        // Уведомление подписчиков об изменении состояния маркеров
        if (!selectedMarkers.isEmpty()) notifyMarkersSelection(selectedMarkers, true);
        if (!unselectedMarkers.isEmpty()) notifyMarkersSelection(unselectedMarkers, false);
    }

    /**
     * Выделяет (снимает выделение) все маркеры на карте
     * @param selected состояние выделения
     */
    public void selectAll(boolean selected) {

        List<SubscriberMarker> selectedMarkers = new ArrayList<>(markers);

        notifyMarkersSelection(selectedMarkers, selected);
    }

    /**
     * Возвращает пиксельную точку местоположения для переданного маркера
     * @param marker маркер, для которого определяется точка
     * @return Point
     */
    public Point getMarkerPoint(Marker marker) {
        // TODO доделать получение Point из LatLong
       // return mapView.toPixels(marker.getLatLong());
        return null;
    }
}
