package tetramap.gui;

import tetramap.config.MapConfig;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.layer.Layer;
import tetramap.leaflet.LeafletControl;
import tetramap.leaflet.LeafletMap;

/**
 * View для карты
 */
public interface MapView {

    /**
     * Отображение карты
     *
     * @param mapConfig файл конфигурации карты
     */
    void displayMap(MapConfig mapConfig);

    /**
     * Установка размеров
     *
     * @param width ширина
     * @param height высота
     */
    void setSize(double width, double height);


    /**
     * Выполнение скрипта на карте
     *
     * @param script текст скрипта
     * @return Object возвращает объект из webView
     */
    Object execScript(String script);

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
     * Получение карты
     *
     * @return LeafletMap карта Leaflet
     */
    LeafletMap getMap();

    /**
     * Добавления слоя на карту
     *
     * @param layer слой на добавление
     */
    void addLayer(Layer layer);

    /**
     * Удаление слоя c карты
     *
     * @param layer слой на удаление c карты
     */
    void removeLayer(Layer layer);

    /**
     * Проверка слоя на exist
     *
     * @param layer слой для проверки на exist
     * @return true если слой существует
     */
    boolean hasLayer(Layer layer);

    /**
     * Добавление Control во View карты
     *
     * @param control контроль для Leaflet
     */
    void addControl(LeafletControl control);

    /**
     * Удаление карты и всех слоев
     *
     */
    void remove();
}
