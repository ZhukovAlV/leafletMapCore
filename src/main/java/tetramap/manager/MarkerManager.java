package tetramap.manager;

import tetramap.entity.marker.SubscriberMarker;
import tetramap.layer.Layer;

import java.util.List;

/**
 * Менеджер для управления маркерами на карте
 */
public interface MarkerManager {

    /**
     * Возвращает список маркеров, которые хранятся данным менеджером
     * @return список маркеров
     */
    List<SubscriberMarker> getMarkers();

    /**
     * Добавляет маркер на карту в качестве слоя
     * @param marker маркер, добаляемый в подписку
     */
    void addMarker(SubscriberMarker marker);

    /**
     * Удаляет маркер с карты
     * @param marker маркер, удаляемый из подписки
     */
    void removeMarker(SubscriberMarker marker);

    /**
     * Удаляет все маркеры с карты
     */
    void clearMarkers();

    /**
     * Уведомление подписчиков об изменении состояния выбора маркера на карте
     * @param marker маркер, который изменяет состояние выбора
     * @param selectionState новое состояние выбора
     */
    void notifyMarkerSelection(SubscriberMarker marker, boolean selectionState);

    /**
     * Уведомление подписчиков об изменении состояния выбора списка маркеров на карте
     * @param markers список маркеров, которые изменяет состояние выбора
     * @param selectionState новое состояние выбора
     */
    void notifyMarkersSelection(List<SubscriberMarker> markers, boolean selectionState);

    /**
     * Выбирает все маркеры по указанному слою (поддерживаются полигоны и круги)
     * @param layer Layer
     */
    void selectMarkersInLayer(Layer layer);
}
