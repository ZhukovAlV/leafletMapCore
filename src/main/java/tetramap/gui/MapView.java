package tetramap.gui;

import tetramap.config.MapConfig;
import tetramap.entity.types.LatLongBounds;
import tetramap.event.MapLeftClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapRightClickEventListener;
import tetramap.layer.Layer;
import tetramap.layer.groups.LayerGroup;
import tetramap.leaflet.LeafletMap;
import tetramap.manager.MarkerManager;
import tetramap.route.RouteManager;

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
     * Добавление слушателя на нажатие левой кнопки мыши
     */
    void addLeftMouseClickListener(MapLeftClickEventListener mapLeftClickEventListener);

    /**
     * Удаление слушателя на нажатие левой кнопки мыши
     */
    void removeLeftMouseClickListener(MapLeftClickEventListener mapLeftClickEventListener);

    /**
     * Добавление слушателя на нажатие правой кнопки мыши
     */
    void addRightMouseClickListener(MapRightClickEventListener mapRightClickEventListener);

    /**
     * Удаление слушателя на нажатие левой правой мыши
     */
    void removeRightMouseClickListener(MapRightClickEventListener mapRightClickEventListener);

    /**
     * Добавление слушателя на перемещение мыши
     */
    void addMouseMoveListener(MapMoveEventListener mapMoveEventListener);

    /**
     * Удаление слушателя на перемещение мыши
     */
    void removeMouseMoveListener(MapMoveEventListener mapMoveEventListener);

    /**
     * Получение карты
     *
     * @return LeafletMap карта Leaflet
     */
    LeafletMap getMap();

    RouteManager getRouteManager();

    /**
     * Добавления слоя на карту
     *
     * @param layer слой на добавление
     */
    void addLayer(Layer layer);

    /**
     * Обновление слоя на карте
     *
     * @param layer слой на обновление
     */
    void updateLayer(Layer layer);

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

    /**
     * Задание зоны видимости
     * @param latLongBounds LatLongBounds - 2 крайние точки (юго-западная и северо-восточная)
     */
    void setMaxBounds(LatLongBounds latLongBounds);

    /**
     * Получение менеджера маркеров
     * @return менеджер маркеров
     */
    MarkerManager getMarkerManager();
}