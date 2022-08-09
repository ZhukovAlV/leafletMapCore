package tetramap.draw;

import tetramap.entity.LatLong;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.gui.MapView;
import tetramap.gui.MapViewLeaflet;

public class CircleDrawAdapter implements MapClickEventListener, MapMoveEventListener {

    // Точка центра
    LatLong centerPoint;

    @Override
    public void mouseMoved(LatLong latLong, MapView map) {

/*        // Только в процессе редактирования (счетчик нажатий == 1)
        if (clickCount % 2 == 1) {
            // Граница круга следует за положением курсора мыши
            shapeLayer.setRadius((float) centerPoint.sphericalDistance(
                    mapView.getMapViewProjection().fromPixels(e.getX(), e.getY()
                    )));

            mapView.getLayerManager().redrawLayers();
        }*/
    }

    @Override
    public void mouseClicked(LatLong latLong, MapView map) {

        ((MapViewLeaflet)map).execScript("L.circle([" + latLong.getLatitude() + ", "+ latLong.getLongitude() + "], {radius: 200}).addTo(map)");
/*        // Если нажата ЛКМ
        if (SwingUtilities.isLeftMouseButton(e)) {

            if (clickCount % 2 == 0) {
                // Переход в режим редактирования
                notifyOnLayerStateChanged(false);
                // Установка указанной позиции центром окружности
                centerPoint = getLatLong(e);
                shapeLayer.setLatLong(centerPoint);
                shapeLayer.setRadius(0);
            } else {
                // Завершение редактирования
                if (mapView.getSubscriberMarkerManager() != null) {
                    // Выбор маркеров в построенной зоне
                    (mapView.getSubscriberMarkerManager()).selectMarkersInCircle(shapeLayer);
                }
                notifyOnLayerStateChanged(true);
            }
        }

        clickCount++;
        // Обновляем слои на карте
        mapView.getLayerManager().redrawLayers();*/
    }
}
