package tetramap.entity.selection;

import tetramap.entity.vectors.structure.LatLongArray;

import java.util.OptionalDouble;

/**
 * Класс, используемый в качестве модели области выделения на карте для сохранения / загрузки в файл
 */
public class Selection {

    /**
     * Тип выделения для модели области выделения
     */
    public enum  SelectionType {
        RECTANGLE,
        CIRCLE,
        POLYGON
    }

    private final SelectionType selectionType;
    // Точки, используемые для построения фигуры
    private final LatLongArray selectionPoints;
    // Радиус, используется для модели круга
    private OptionalDouble radius = OptionalDouble.empty();

    /**
     * @param selectionType тип выделения
     * @param selectionPoints вершины выделения для многоугольника / центр для круга
     */
    public Selection(SelectionType selectionType, LatLongArray selectionPoints) {
        this.selectionType = selectionType;
        this.selectionPoints = selectionPoints;
    }

    /**
     * Возвращает тип выделения для данной модели
     * @return тип выделения для данной модели
     */
    public SelectionType getSelectionType() {
        return selectionType;
    }

    /**
     * Возвращает вершины, используемые для построения выделения
     * @return вершины, используемые для построения выделения
     */
    public LatLongArray getSelectionLatLongs() {
        return selectionPoints;
    }

    /**
     * Возвращает радиус, если данная модель для круга (для остальных типов возвращается {@link OptionalDouble#empty()})
     * @return радиус, если данная модель для круга
     */
    public OptionalDouble getRadius() {
        return radius;
    }

    /**
     * Устанавливает радиус для данной модели (используется только для модели круга)
     * @param radius радиус для данной модели
     */
    public void setRadius(OptionalDouble radius) {
        this.radius = radius;
    }
}
