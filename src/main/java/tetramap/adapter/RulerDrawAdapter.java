package tetramap.adapter;

import lombok.extern.log4j.Log4j2;
import tetramap.entity.types.LatLong;
import tetramap.event.DrawShapeEndEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.gui.MapView;
import tetramap.util.LatLongUtils;

/**
 * Класс инструмента "Линейка"
 *
 * Линейка представляет собой ломаную линию из точек. На последней точке линейки располагается маркер
 * с указанием общей длины линии (в метрах). Также
 */
@Log4j2
public class RulerDrawAdapter extends PolylineDrawAdapter implements MapMoveEventListener, DrawShapeEndEventListener {

    // Подтвержденная дистанция - метры (не учитывается последняя точка, которая движется за курсором в режиме редактирования)
    protected double confirmedDistance = 0;

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

        log.info("Дистанция: " + confirmDistance() + " метров");
    }

    /**
     * Считает и возвращает общую длину линии (в метрах)
     * Длина после этого считается подтвержденной, т.е. пользователь подтвердил (добавил) новую точку
     * @return общая длина в метрах
     */
    private double confirmDistance() {

        double distance = 0;
        for (int i = 1; i < getListLatLong().size(); i++) {
            distance += LatLongUtils.sphericalDistance(getListLatLong().get(i), getListLatLong().get(i - 1));
        }

        this.confirmedDistance = distance;
        return confirmedDistance;
    }

    @Override
    public void mouseMoved(LatLong latLong) {
       // log.info(latLong);
    }

    @Override
    public void drawEnd(String shape) {
        log.info("Фигура нарисована: " + shape);
    }
}
