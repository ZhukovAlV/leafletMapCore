package tetramap.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tetramap.entity.types.LatLong;

import java.util.List;

/**
 * Результат поиска маршрута движения
 * Объединяет точки маршрута, общую дистанцию и время в пути
 */
@Getter
@AllArgsConstructor
public class RouteResult {

    /**
     * Точки маршрута движения
     */
    private List<LatLong> latLongs;

    /**
     * Общая длина пути (м)
     */
    private double distance;

    /**
     * Общее время в пути (с) - Зависит от типа транспортного средства и настроек скорости
     */
    private double time;
}