package tetramap.gui;

import javafx.concurrent.Worker;
import tetramap.config.MapConfig;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;

import java.util.concurrent.CompletableFuture;

/**
 * Карта
 */
public interface MapView {
    /**
     * Отображение карты с заданными настрйоками
     * @param mapConfig настройки карты
     * @return CompletableFuture
     */
    CompletableFuture<Worker.State> displayMap(MapConfig mapConfig);

    /**
     * Установка размеров
     * @param width ширина
     * @param height высота
     */
    void setSize(double width, double height);

    /**
     * Добавление слушателя на нажатие мыши
     */
    void addMouseClickListener(MapClickEventManager mapClickEventManager);

    /**
     * Добавление слушателя на перемещение мыши
     */
    void addMouseMoveListener(MapMoveEventListener mapMoveEventListener);
}
