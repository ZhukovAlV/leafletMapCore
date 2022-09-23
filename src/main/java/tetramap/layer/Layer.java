package tetramap.layer;


import tetramap.entity.BasicType;
import tetramap.entity.impl.LMapLeaflet;

/**
 * Leaflet методы для слоев
 */
public interface Layer extends BasicType {

    /**
     * Создание слоя на карте
     * @param LMapLeaflet карта
     */
    default void createTo(LMapLeaflet LMapLeaflet) {
        LMapLeaflet.createLayer(this);
    }

    /**
     * Добавления слоя на карту
     * @param LMapLeaflet карта
     */
    default void addTo(LMapLeaflet LMapLeaflet) {
        LMapLeaflet.addLayer(this);
    }
}
