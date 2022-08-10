package tetramap.draw;

import tetramap.entity.Circle;
import tetramap.entity.LatLong;
import tetramap.gui.MapViewLeaflet;
import tetramap.util.LatLongUtil;

public class CircleDrawAdapterLeaflet implements CircleDrawAdapter {

    // Карта
    private final MapViewLeaflet mapView;

    // Круг
    private Circle circle;

    // Объект еще рисуется или нет
    boolean isShapeComplete = false;

    // Счетчик кликов на карте (для определения стадии редактирования)
    public int clickCount = 0;

    public CircleDrawAdapterLeaflet(MapViewLeaflet mapView) {
        this.mapView = mapView;
        circle = new Circle();
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        if (circle.getCenterPoint() != null && !isShapeComplete) {
            circle.setRadius(LatLongUtil.sphericalDistance(circle.getCenterPoint(), latLong));
            mapView.execScript("circle.setRadius(" + circle.getRadius() + ")");
        }
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        // Если рисуем фигуру повторно удаляем предыдущий круг
        if (isShapeComplete && circle.getCenterPoint() != null) clear();

        clickCount++;

        // Если второй раз нажали ЛКМ, завершаем рисование круга и удаляем слушателей
        if (clickCount % 2 == 0) {
            isShapeComplete = true;
            mapView.removeMouseClickListener(this);
            mapView.removeMouseMoveListener(this);

        // Если первый раз нажали ЛКМ, то рисуем круг с радиусом 0 по умолчанию
        }
        else if (!isShapeComplete) {
            circle.setCenterPoint(latLong);
            mapView.execScript("var circle = L.circle([" + latLong.getLatitude() + ", "+
                    latLong.getLongitude() + "], {radius: " + circle.getRadius() + " }).addTo(map)");
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
        circle = new Circle();
    }

    @Override
    public void draw() {
        mapView.addMouseClickListener(this);
        mapView.addMouseMoveListener(this);
    }
}
