package tetramap.entity;

import lombok.Data;

@Data
public class CircleLeaflet implements Circle {

    /**
     * Центер круга
     */
    private LatLong centerPoint;

    /**
     * Радиус
     */
    private double radius;
}
