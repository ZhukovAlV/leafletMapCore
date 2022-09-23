package tetramap.layer;


import tetramap.entity.BasicType;
import tetramap.gui.ViewContainer;

/**
 * Leaflet методы для слоев
 */
public interface Layer extends BasicType {

    /**
     * Создание слоя на карте
     * @param viewContainer карта
     */
    default void createTo(ViewContainer viewContainer) {
        viewContainer.createLayer(this);
    }

    /**
     * Добавления слоя на карту
     * @param viewContainer карта
     */
    default void addTo(ViewContainer viewContainer) {
        viewContainer.addLayer(this);
    }
}
