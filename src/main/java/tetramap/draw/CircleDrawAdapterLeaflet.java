package tetramap.draw;

import tetramap.entity.CircleLeaflet;
import tetramap.entity.LatLong;
import tetramap.gui.MapViewLeaflet;
import tetramap.util.LatLongUtil;

public class CircleDrawAdapterLeaflet implements CircleDrawAdapter {

    // Карта
    private final MapViewLeaflet mapView;

    // Круг
    private CircleLeaflet circleLeaflet;

    // Объект еще рисуется или нет
    boolean isShapeComplete = false;

    // Счетчик кликов на карте (для определения стадии редактирования)
    public int clickCount = 0;

    public CircleDrawAdapterLeaflet(MapViewLeaflet mapView) {
        this.mapView = mapView;
        circleLeaflet = new CircleLeaflet();
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        if (circleLeaflet.getCenterPoint() != null && !isShapeComplete) {
            circleLeaflet.setRadius(LatLongUtil.sphericalDistance(circleLeaflet.getCenterPoint(), latLong));
            mapView.execScript("circle.setRadius(" + circleLeaflet.getRadius() + ");");
        }
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        // Если рисуем фигуру повторно удаляем предыдущий круг
        if (isShapeComplete && circleLeaflet.getCenterPoint() != null) clear();

        clickCount++;

        // Если второй раз нажали ЛКМ, завершаем рисование круга и удаляем слушателей
        if (clickCount % 2 == 0) {
            isShapeComplete = true;
            mapView.removeMouseClickListener(this);
            mapView.removeMouseMoveListener(this);

        // Если первый раз нажали ЛКМ, то рисуем круг с радиусом 0 по умолчанию
        }
        else if (!isShapeComplete) {
            circleLeaflet.setCenterPoint(latLong);
            mapView.execScript("var circle = L.circle([" + latLong.getLatitude() + ", "+
                    latLong.getLongitude() + "], {radius: " + circleLeaflet.getRadius() + " }).addTo(map);");
        }

        // TODO выбор маркеров в заданной зоне реализовать
    }

    @Override
    public boolean isShapeComplete() {
        return isShapeComplete;
    }

    @Override
    public void setShapeComplete(boolean shapeComplete) {
        isShapeComplete = shapeComplete;
    }

    @Override
    public void clear() {
        mapView.execScript("circle.remove()");
        clickCount = 0;
        isShapeComplete = false;
        circleLeaflet = new CircleLeaflet();
    }

    @Override
    public void draw() {
        mapView.addMouseClickListener(this);
        mapView.addMouseMoveListener(this);
    }
}
