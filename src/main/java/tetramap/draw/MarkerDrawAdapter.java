package tetramap.draw;

import tetramap.event.MapClickEventListener;

public interface MarkerDrawAdapter extends MapClickEventListener {

    /**
     * Удаление фигуры круга
     */
    void clear();

    /**
     * Рисование маркеров
     */
     void draw();
}
