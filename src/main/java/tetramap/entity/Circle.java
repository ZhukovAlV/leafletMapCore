package tetramap.entity;

import lombok.Data;

@Data
public class Circle {
    // Точка центра
    private LatLong centerPoint;

    // Радиус
    private double radius;
}
