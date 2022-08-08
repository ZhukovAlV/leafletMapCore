package tetramap.gui;

import tetramap.config.MapConfig;

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
    CompletableFuture displayMap(MapConfig mapConfig);

    /**
     * Установка размеров
     * @param width ширина
     * @param height высота
     */
    void setSize(double width, double height);
}
