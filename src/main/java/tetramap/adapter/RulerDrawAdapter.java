package tetramap.adapter;

import lombok.extern.log4j.Log4j2;
import tetramap.entity.marker.Marker;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
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
public class RulerDrawAdapter extends PolylineDrawAdapter implements MapMoveEventListener {

    private final String START_DISTANCE = "Начало дистанции";

    // Подтвержденная дистанция - метры (не учитывается последняя точка, которая движется за курсором в режиме редактирования)
    private int confirmedDistance;

    // Маркер для отображения дистанции
    private Marker marker;

    // Прерывистая линия для рисования дистанции
    Polyline brokenLine;

    public RulerDrawAdapter(MapView mapView) {
        super(mapView);
    }

    @Override
    public void onInvoke() {
        super.onInvoke();

        getMapView().addMouseMoveListener(this);
    }

    @Override
    public void onRevoke() {
        super.onRevoke();

        getMapView().removeMouseMoveListener(this);
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        super.mouseClicked(latLong);

        // Добавляем новую координату и обновляем polyline
        ((LatLongArray)getPolyline().getLatLongs()).add(latLong);
        getPolyline().updateTo();

        // Расчитываем расстояние
        String distanceString = "Расстояние: " + confirmDistance() + " метров";
        log.info(distanceString);

        // Выставляем значение расстояния
        if (marker != null) {
            marker.setLatLong(latLong);
            marker.updateTo();

            marker.bindTooltip(distanceString);
        } else {
            marker = new Marker(latLong);
            getMapView().getLayerGroup().addLayer(marker);

            marker.bindTooltip(START_DISTANCE);
        }
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        List<LatLong> latLongs = new ArrayList<>((LatLongArray)getPolyline().getLatLongs());
        latLongs.add(latLong);

        double distance = countingDistance(latLongs);
        String distanceString = "Расстояние: " + (int)distance + " метров";

        // Выставляем значение расстояния
        if (marker != null) {

            if (brokenLine == null) {
                brokenLine = new Polyline(latLongs.get(latLongs.size() - 1), latLong);
                getMapView().getLayerGroup().addLayer(brokenLine);
            } else {
                brokenLine.remove();

                LatLong[] arrLatLong = new LatLong[]{latLongs.get(latLongs.size() - 1), latLong};
                brokenLine.setLatLongs(new LatLongArray(arrLatLong));

                brokenLine.updateTo();
            }

            marker.setLatLong(latLong);
            marker.updateTo();

            marker.bindTooltip(distanceString);
        }
    }

    /**
     * Считает и возвращает общую длину линии (в метрах)
     * Длина после этого считается подтвержденной, т.е. пользователь подтвердил (добавил) новую точку
     * @return общая длина в метрах
     */
    private int confirmDistance() {
        double distance = countingDistance((LatLongArray)getPolyline().getLatLongs());
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
}
