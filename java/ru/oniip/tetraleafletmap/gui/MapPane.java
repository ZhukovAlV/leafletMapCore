package ru.oniip.tetraleafletmap.gui;

public interface MapPane {

    /**
     * Инициализация панели с картой и функционалом для управления ей
     */
    void initialize();

    /**
     * Масштабирование карты
     */
    void resizeMap();

    /**
     * Изменение расположения панели с кнопками
     */
    void updatePanelHeight();

    /**
     * Получение View карты (MapView)
     */
    LeafletMapView getMapView();
}
