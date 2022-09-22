package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Точка на карте
 */
@Data
@AllArgsConstructor
public class Point implements Serializable {

    @Serial
    private static final long serialVersionUID = -4978055088391693282L;

    private double x;
    private double y;

/*    public double distanceTo(Point target) {
        if (target == null) {
            return -1;
        }
        double dx = target.x - this.x;
        double dy = target.y - this.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean contains(Point point) {
        if (point == null) {
            return false;
        }
        return Math.abs(point.x) <= Math.abs(this.x) && Math.abs(point.y) <= Math.abs(this.y);
    }*/

    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
