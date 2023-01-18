package tetramap.draw;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.RectangleDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Rectangle;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.gui.MapView;

@Log4j2
@Getter
public class RectangleDrawAdapterImpl implements RectangleDrawAdapter {

    /**
     * Карта
     */
    private final MapView mapView;

    /**
     * Прямоугольник
     */
    private final Rectangle rectangle;

    public RectangleDrawAdapterImpl(MapView mapView) {
        this.mapView = mapView;

        rectangle = new Rectangle();
        rectangle.getPathOptions().setFill(true);
    }

    @Override
    public void onInvoke() {
        mapView.addLeftMouseClickListener(this);
        mapView.addMouseMoveListener(this);
    }

    @Override
    public void onRevoke() {
        removeListener();

        if (!rectangle.getLatLongs().isEmpty()) mapView.getLayerGroup().removeLayer(rectangle);

        // Очищаем координаты
        ((LatLongArray)rectangle.getLatLongs()).clear();
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        LatLongArray latLongArray = (LatLongArray) rectangle.getLatLongs();

        // Добавляем прямоугольник на карту, тольк если имеются 2 точки
        if (latLongArray.isEmpty()) {
            latLongArray.add(latLong);
            mapView.getLayerGroup().addLayer(rectangle);

        } else {
            checkLatLongArray(latLongArray, latLong);
            removeListener();
        }
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        LatLongArray latLongArray = (LatLongArray) rectangle.getLatLongs();

        // Для рисования квадрата всегда перезатираем вторую точку
        if (!latLongArray.isEmpty()) {
            checkLatLongArray(latLongArray, latLong);
            rectangle.updateTo();
        }
    }

    private void checkLatLongArray(LatLongArray latLongArray, LatLong latLong) {
        if (latLongArray.size() == 1) latLongArray.add(1, latLong);
            else latLongArray.set(1, latLong);
    }

    private void removeListener() {
        mapView.removeLeftMouseClickListener(this);
        mapView.removeMouseMoveListener(this);
    }
}