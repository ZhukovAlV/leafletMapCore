package tetramap.gui;

import tetramap.config.MapConfig;
import tetramap.event.DrawShapeEndEventListener;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.layer.Layer;
import tetramap.layer.groups.LayerGroup;
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
     * Удаление слушателя на перемещение мыши
     */
    void removeMouseMoveListener(MapMoveEventListener mapMoveEventListener);

    /**
     * Добавление слушателя на окончание рисования фигуры
     */
    void addDrawEndShapeListener(DrawShapeEndEventListener drawShapeEndEventListener);

    /**
     * Удаление слушателя на окончание рисования фигуры
     */
    void removeDrawEndShapeListener(DrawShapeEndEventListener drawShapeEndEventListener);

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
     * Удаление карты и всех слоев
     *
     */
    void remove();

    /**
     * Уменьшение масштаба карты
     */
    void zoomIn();

    /**
     * Увеличение масштаба карты
     */
    void zoomOut();

    /**
     * Переход к центру карты
     */
    void moveToCenter();

    /**
     * Получения всех слоев на карте
     * @return LayerGroup слоев на карте
     */
    LayerGroup getLayerGroup();
}
