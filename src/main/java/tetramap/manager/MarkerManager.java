package tetramap.manager;

import tetramap.entity.marker.Marker;
import tetramap.event.MarkerEventListener;

import java.util.List;

/**
 * Менеджер для управления маркерами на карте
 */
public interface MarkerManager {

    /**
     * Возвращает список маркеров, которые хранятся данным менеджером
     * @return список маркеров
     */
    List<MarkerEventListener> getMarkers();

    /**
     * Добавляет маркер на карту в качестве слоя
     * @param markerEventListener маркер, добаляемый в подписку
     */
    void addMarker(MarkerEventListener markerEventListener);

    /**
     * Удаляет маркер с карты
     * @param markerEventListener маркер, удаляемый из подписки
     */
    void removeMarker(MarkerEventListener markerEventListener);

    /**
     * Удаляет все маркеры с карты
     */
    void clearMarkers();

    /**
     * Уведомление подписчиков об изменении состояния выбора маркера на карте
     * @param marker маркер, который изменяет состояние выбора
     * @param selectionState новое состояние выбора
     */
    void notifyMarkerSelection(Marker marker, boolean selectionState);

    /**
     * Уведомление подписчиков об изменении состояния выбора списка маркеров на карте
     * @param markers список маркеров, которые изменяет состояние выбора
     * @param selectionState новое состояние выбора
     */
    void notifyMarkersSelection(List<Marker> markers, boolean selectionState);
}
