package tetramap.layer;

/**
 * Для слоев в layer.pm (geoman) доступны следующие методы:
 */
public interface LayerGeoman {

    /**
     * Отключает режим редактирования
     */
    void disable();

    /**
     * Возвращает true, если включен режим редактирования. false при отключении
     *
     * @return true, если включен режим редактирования
     */
    boolean enabled();

    /**
     * Возвращает true, если линия или многоугольник имеют самопересечение
     *
     * @return true, если линия или многоугольник имеют самопересечение
     */
    boolean hasSelfIntersection();

    /**
     * Удаляет слой
     */
    void remove();
}
