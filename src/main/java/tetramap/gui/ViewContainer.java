package tetramap.gui;

import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import tetramap.config.MapConfig;
import tetramap.layer.Layer;

import java.util.concurrent.CompletableFuture;

public interface ViewContainer {

    /**
     * Отображение карты
     * @param mapConfig файл конфигурации карты
     * @return CompletableFuture
     */
    CompletableFuture<Worker.State> displayMap(MapConfig mapConfig);

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
