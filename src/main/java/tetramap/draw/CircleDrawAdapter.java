package tetramap.draw;

import tetramap.event.MapClickEventListener;
import tetramap.event.MapMoveEventListener;

public interface CircleDrawAdapter extends MapClickEventListener, MapMoveEventListener {
    /**
     * Закончено рисование фигуры или нет
     * @return да, если круг нарисован
     */
    boolean isShapeComplete();

    /**
     * Установка завершенности рисования фигуры
     * @param shapeComplete текущее состояние
     */
    void setShapeComplete(boolean shapeComplete);

    /**
     * Удаляем фигуру круга
     */
    void clearShape();
}
