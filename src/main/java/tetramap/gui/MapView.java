package tetramap.gui;

import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import tetramap.config.MapConfig;
import tetramap.entity.LMap;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.layer.Layer;

import java.util.concurrent.CompletableFuture;

/**
 * Карта
 */
public interface MapView {


    /**
     * Отображение карты
     * @param mapConfig файл конфигурации карты
     * @return CompletableFuture
     */
    CompletableFuture<Worker.State> displayMap(MapConfig mapConfig);

    /**
     * Получение карты
     * @return LeafletMap
     */
    LMap getMap();

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

    /**
     * Выполнение скрипта на карте
     * @param script текст скрипта
     */
    void execScript(String script);

    /**
     * Возвращает WebView
     * @return WebView
     */
    WebView getWebView();

    /**
     * Добавления слоя на карту
     * @param layer слой на добавление
     */
    void addLayer(Layer layer);
}
