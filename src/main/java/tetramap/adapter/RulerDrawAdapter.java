package tetramap.adapter;

import tetramap.entity.types.LatLong;
import tetramap.gui.MapView;
import tetramap.util.LatLongUtils;

/**
 * Класс инструмента "Линейка"
 *
 * Линейка представляет собой ломаную линию из точек. На последней точке линейки располагается маркер
 * с указанием общей длины линии (в метрах). Также
 */
public class RulerDrawAdapter extends PolylineDrawAdapter{

    // Подтвержденная дистанция - метры (не учитывается последняя точка, которая движется за курсором в режиме редактирования)
    protected double confirmedDistance = 0;

    public RulerDrawAdapter(MapView mapView) {
        super(mapView);
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        super.mouseClicked(latLong);

        System.out.println(confirmDistance());
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
}
