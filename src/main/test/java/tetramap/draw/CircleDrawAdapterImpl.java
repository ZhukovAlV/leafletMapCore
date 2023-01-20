package tetramap.draw;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.CircleDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Circle;
import tetramap.gui.MapView;
import tetramap.util.LatLongUtils;

@Log4j2
@Getter
public class CircleDrawAdapterImpl implements CircleDrawAdapter {

    /**
     * Карта
     */
    private final MapView mapView;

    /**
     * Круг
     */
    private Circle circle;

    public CircleDrawAdapterImpl(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void onInvoke() {
        mapView.addLeftMouseClickListener(this);
        mapView.addMouseMoveListener(this);
    }

    @Override
    public void onRevoke() {
        removeListener();

        // Обнуляем данные о круге
        if (circle != null && mapView.getLayerGroup().hasLayer(circle)) mapView.getLayerGroup().removeLayer(circle);

        circle = null;
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        if (circle == null) {
            circle = new Circle(latLong);
            circle.getPathOptions().setFill(true);

            mapView.getLayerGroup().addLayer(circle);
        } else {
            updateRadius(latLong);

            removeListener();

            // Обновляем маркеры в области выделения
            mapView.getMarkerManager().selectMarkersInLayer(circle);
        }
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        if (circle != null) {
            updateRadius(latLong);
        }
    }

    private void updateRadius(LatLong latLong) {
        double distance = LatLongUtils.sphericalDistance(circle.getCenter(), latLong);
        circle.setRadius(distance);

        circle.updateTo();
    }

    private void removeListener() {
        mapView.removeLeftMouseClickListener(this);
        mapView.removeMouseMoveListener(this);
    }
}