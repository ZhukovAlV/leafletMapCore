package tetramap.draw;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.CircleDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Circle;
import tetramap.gui.MapView;
import tetramap.util.LatLongUtils;

@Log4j2
@Getter
@Setter
public class CircleDrawAdapterImpl implements CircleDrawAdapter {

    /**
     * Карта
     */
    private final MapView mapView;

    /**
     * Статус подключения
     */
    private boolean isInvoke;

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

        isInvoke = true;
    }

    @Override
    public void onRevoke() {
        if (isInvoke) removeListeners();

        if (circle != null) {
            // Обнуляем данные о круге
            if (mapView.getLayerGroup().hasLayer(circle)) mapView.getLayerGroup().removeLayer(circle);
        }

        circle = null;

        isInvoke = false;
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        if (circle == null) {
            circle = new Circle(latLong);
            circle.getPathOptions().setFill(true);

            mapView.getLayerGroup().addLayer(circle);

        } else {
            updateRadius(latLong);

            removeListeners();

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

    @Override
    public boolean isInvoked() {
        return isInvoke;
    }

    @Override
    public void removeListeners() {
        mapView.removeLeftMouseClickListener(this);
        mapView.removeMouseMoveListener(this);
    }
}