package tetramap.draw;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.PolygonDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polygon;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.gui.MapView;

import java.util.ArrayList;

/**
 * Обработчик для рисования области выделения полигоном (многоугольником) на карте
 */
@Log4j2
@Getter
public class PolygonDrawAdapterImpl implements PolygonDrawAdapter {

    /**
     * Карта
     */
    private final MapView mapView;

    /**
     * Многоугольник
     */
    private final Polygon polygon;

    /**
     * Прерывистая линия и временная фигура на основе которой будет построен многоугольник Polygon
     */
    private final Polyline brokenLine;
    private final Polyline polyline;

    public PolygonDrawAdapterImpl(MapView mapView) {
        this.mapView = mapView;

        polyline = new Polyline();
        polyline.getPathOptions().setFill(true);

        brokenLine = new Polyline();
        brokenLine.getPathOptions().setDashOffset("10");
        brokenLine.getPathOptions().setDashArray("10, 10");

        polygon = new Polygon();
        polygon.getPathOptions().setFill(true);
    }

    @Override
    public void onInvoke() {
        // Добавляем объекты на карту
        mapView.getLayerGroup().addLayer(polyline);
        mapView.getLayerGroup().addLayer(brokenLine);

        mapView.addLeftMouseClickListener(this);
        mapView.addRightMouseClickListener(this);
        mapView.addMouseMoveListener(this);
    }

    @Override
    public void onRevoke() {
        removeListener();

        mapView.getLayerGroup().removeLayer(brokenLine);
        mapView.getLayerGroup().removeLayer(polyline);
        if (!polygon.isEmpty()) mapView.getLayerGroup().removeLayer(polygon);

        // Очищаем координаты
        ((LatLongArray)brokenLine.getLatLongs()).clear();
        ((LatLongArray)polyline.getLatLongs()).clear();
        ((LatLongArray)polygon.getLatLongs()).clear();
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        // Добавляем новую координату и обновляем polyline
        ((LatLongArray)polyline.getLatLongs()).add(latLong);
        polyline.updateTo();
    }

    @Override
    public void rightMouseClicked() {
        ((LatLongArray)polygon.getLatLongs()).addAll((LatLongArray)polyline.getLatLongs());
        mapView.getLayerGroup().addLayer(polygon);

        removeListener();

        LatLongArray latLongArray = ((LatLongArray)polygon.getLatLongs());
        if (latLongArray.size() < 2) {
            latLongArray.clear();
            polygon.updateTo();
        }

        // Очищаем координаты
        ((LatLongArray) brokenLine.getLatLongs()).clear();
        ((LatLongArray) polyline.getLatLongs()).clear();

        brokenLine.updateTo();
        polyline.updateTo();

        // Обновляем маркеры в области выделения
        mapView.getMarkerManager().selectMarkersInLayer(polygon);
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        ArrayList<LatLong> listLatLong = (LatLongArray)getPolyline().getLatLongs();

        if (!listLatLong.isEmpty()) {
            // Получаем массив координат нашей прерывистой линии
            LatLongArray latLongArray = (LatLongArray)brokenLine.getLatLongs();

            // Очищаем предыдущие координаты временной линии
            latLongArray.clear();

            // Добавляем начальную координату прерывистой линии
            latLongArray.add(listLatLong.get(listLatLong.size() - 1));

            // Добавляем конечную координату прерывистой линии
            latLongArray.add(latLong);

            // Обновляем объект на карте
            brokenLine.updateTo();
        }
    }

    private void removeListener() {
        mapView.removeLeftMouseClickListener(this);
        mapView.removeRightMouseClickListener(this);
        mapView.removeMouseMoveListener(this);
    }
}