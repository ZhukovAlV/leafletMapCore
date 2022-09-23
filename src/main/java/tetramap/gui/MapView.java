package tetramap.gui;

import javafx.concurrent.Worker;
import tetramap.config.MapConfig;
import tetramap.entity.LMap;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapMoveEventListener;

import java.util.concurrent.CompletableFuture;

/**
 * Карта
 */
public interface MapView {

    /**
     * Отображение карты с заданными настрйоками
     *
     * @param mapConfig настройки карты
     * @return CompletableFuture
     */
    CompletableFuture<Worker.State> displayMap(MapConfig mapConfig);

    /**
     * Получение карты
     * @return LeafletMap
     */
    ViewContainer getMap();

    /**
     * Установка размеров
     *
     * @param width ширина
     * @param height высота
     */
    void setSize(double width, double height);

    /**
     * Добавление слушателя на нажатие мыши
     */
    void addMouseClickListener(MapClickEventListener mapClickEventListener);

    /**
     * Удаление слушателя на нажатие мыши
     */
    void removeMouseClickListener(MapClickEventListener mapClickEventListener);

    /**
     * Добавление слушателя на перемещение мыши
     */
    void addMouseMoveListener(MapMoveEventListener mapMoveEventListener);

    /**
     * Добавление слушателя на перемещение мыши
     */
    void removeMouseMoveListener(MapMoveEventListener mapMoveEventListener);
}
