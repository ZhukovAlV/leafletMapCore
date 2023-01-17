package tetramap.draw;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.RulerDrawAdapter;
import tetramap.entity.marker.Marker;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
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
@Getter
public class RulerDrawAdapterImpl extends PolylineDrawAdapterImpl implements RulerDrawAdapter {

    private final String START_DISTANCE = "Начало дистанции";

    // Подтвержденная дистанция - метры (не учитывается последняя точка, которая движется за курсором в режиме редактирования)
    private int distance;

    // Маркер для отображения дистанции
    private Marker marker;

    // Прерывистая линия для рисования дистанции
    private Polyline brokenLine;

    public RulerDrawAdapterImpl(MapView mapView) {
        super(mapView);

        // Задаем цвет линии красный
        getPolyline().getPathOptions().setColor("red");
        getPolyline().getPathOptions().setFillColor("red");
    }

    @Override
    public void onInvoke() {
        super.onInvoke();

        getMapView().addMouseMoveListener(this);
        getMapView().addRightMouseClickListener(this);
    }

    @Override
    public void onRevoke() {
        super.onRevoke();

        getMapView().removeMouseMoveListener(this);
        getMapView().removeRightMouseClickListener(this);

        marker = null;

        ((LatLongArray)getPolyline().getLatLongs()).clear();
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        super.leftMouseClicked(latLong);

        // Расчитываем расстояние
        String distanceString = confirmDistance() + " м";
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
    public void rightMouseClicked() {
        if (marker != null && brokenLine != null) {
            brokenLine.remove();

            LatLongArray latLongArray = (LatLongArray)getPolyline().getLatLongs();
            List<LatLong> latLongs = new ArrayList<>(latLongArray);

            String distanceString = "Расстояние: " + confirmDistance() + " м";
            log.info(distanceString);

            marker.setLatLong(latLongs.get(latLongs.size() - 1));
            marker.updateTo();
            marker.bindTooltip(distanceString);
        }

        onRevoke();
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        LatLongArray latLongArray = (LatLongArray)getPolyline().getLatLongs();
        latLongArray.add(latLong);

        double distance = countingDistance(latLongArray);
        String distanceString = (int)distance + " м";

        // Выставляем значение расстояния
        if (marker != null) {

            if (brokenLine == null) {
                brokenLine = new Polyline(latLongArray.get(latLongArray.size() - 2), latLong);

                // Делаем прерывистую линию
                brokenLine.getPathOptions().setDashArray("5, 7");

                // Задаем цвет линии прерывистой
                brokenLine.getPathOptions().setColor("red");
                brokenLine.getPathOptions().setFillColor("red");

                getMapView().getLayerGroup().addLayer(brokenLine);
            } else {
                brokenLine.remove();

                LatLong[] arrLatLong = new LatLong[]{latLongArray.get(latLongArray.size() - 2), latLong};
                brokenLine.setLatLongs(new LatLongArray(arrLatLong));

                brokenLine.updateTo();
            }

            marker.setLatLong(latLong);
            marker.updateTo();

            marker.bindTooltip(distanceString);
        }

        latLongArray.remove(latLong);
    }

    /**
     * Считает и возвращает общую длину линии (в метрах)
     * Длина после этого считается подтвержденной, т.е. пользователь подтвердил (добавил) новую точку
     * @return общая длина в метрах
     */
    private int confirmDistance() {
        double distance = countingDistance((LatLongArray)getPolyline().getLatLongs());
        this.distance = (int)distance;

        return this.distance;
    }

    private double countingDistance(List<LatLong> latLongs) {
        double distance = 0;
        for (int i = 1; i < latLongs.size(); i++) {
            distance += LatLongUtils.sphericalDistance(latLongs.get(i), latLongs.get(i - 1));
        }

        return distance;
    }
}
