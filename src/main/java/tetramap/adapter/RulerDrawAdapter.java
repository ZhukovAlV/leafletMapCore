package tetramap.adapter;

import lombok.extern.log4j.Log4j2;
import tetramap.entity.marker.Marker;
import tetramap.entity.types.LatLong;
import tetramap.event.DrawShapeEndEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.gui.MapView;
import tetramap.util.LatLongUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс инструмента "Линейка"
 *
 * Линейка представляет собой ломаную линию из точек. На последней точке линейки располагается маркер
 * с указанием общей длины линии (в метрах). Также
 */
@Log4j2
public class RulerDrawAdapter extends PolylineDrawAdapter implements MapMoveEventListener, DrawShapeEndEventListener {

    // Подтвержденная дистанция - метры (не учитывается последняя точка, которая движется за курсором в режиме редактирования)
    private int confirmedDistance;

    // Маркер для отображения дистанции
    private Marker marker;

    public RulerDrawAdapter(MapView mapView) {
        super(mapView);
    }

    @Override
    public void onInvoke() {
        super.onInvoke();

        getMapView().addMouseMoveListener(this);
        getMapView().addDrawEndShapeListener(this);
    }

    @Override
    public void onRevoke() {
        super.onRevoke();

        getMapView().removeMouseMoveListener(this);
        getMapView().removeDrawEndShapeListener(this);
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        super.mouseClicked(latLong);

        String distanceString = "Расстояние: " + confirmDistance() + " метров";
        log.info(distanceString);

        // Выставляем значение расстояния
        if (marker != null) {
            marker.setLatLong(latLong);
            marker.setText(distanceString);
            marker.updateTo();
        } else {
            marker = new Marker(latLong, true, distanceString, "");
            marker.addTo(getMapView());
          //  getMapView().getLayersGeoman().add(marker);
        }
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        List<LatLong> latLongs = new ArrayList<>(getListLatLong());
        latLongs.add(latLong);

        double distance = countingDistance(latLongs);
        String distanceString = "Расстояние: " + (int)distance + " метров";

        // Выставляем значение расстояния
        if (marker != null) {
            marker.setLatLong(latLong);
            marker.setText(distanceString);
            marker.updateTo();
        }
    }

    /**
     * Считает и возвращает общую длину линии (в метрах)
     * Длина после этого считается подтвержденной, т.е. пользователь подтвердил (добавил) новую точку
     * @return общая длина в метрах
     */
    private int confirmDistance() {
        double distance = countingDistance(getListLatLong());
        confirmedDistance = (int)distance;

        return confirmedDistance;
    }

    private double countingDistance(List<LatLong> latLongs) {
        double distance = 0;
        for (int i = 1; i < latLongs.size(); i++) {
            distance += LatLongUtils.sphericalDistance(latLongs.get(i), latLongs.get(i - 1));
        }

        return distance;
    }

    @Override
    public void drawEnd(String shape) {
        log.info("Фигура нарисована: " + shape);

        onRevoke();
    }
}
