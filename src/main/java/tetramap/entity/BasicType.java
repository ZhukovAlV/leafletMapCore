package tetramap.entity;

import tetramap.gui.ViewContainer;

import java.io.Serializable;

/**
 * Базовый тип для Leaflet объектов
 */
public interface BasicType extends Serializable {

    /**
     * Получение класса для Leaflet
     * @return тип класса объекта
     */
    default String getLeafletType() {
        return getClass().getSimpleName();
    }

    /**
     * Возвращает ID объекта
     * @return ID объекта
     */
    String getId();

    /**
     * Возвращает тип экземпляра
     * @return тип экземпляра
     */
    String getTypeInstantiatesMap();

    /**
     * Создание слоя в контейнере HTML
     * @param viewContainer контейнер
     */
    default void createTo(ViewContainer viewContainer) {
        viewContainer.execScript("var " + this.getId() + " = L." + this.getTypeInstantiatesMap() + this + ";");
    }
}