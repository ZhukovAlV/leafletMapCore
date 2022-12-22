package tetramap.entity.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Represents a point with x and y coordinates in pixels.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Serializable {

    private static final long serialVersionUID = -4978055088391693282L;

    private double x;
    private double y;

    /**
     * Returns the cartesian distance between the current and the given points.
     *
     * @param target a valid point
     * @return the distance between the current and the given point
     */
    public double distanceTo(Point target) {
        if (target == null) {
            return -1;
        }
        double dx = target.x - this.x;
        double dy = target.y - this.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns `true` if both coordinates of the given point are less than the
     * corresponding current point coordinates (in absolute values).
     *
     * @param point a valid point
     * @return true if both coordiantes of the given point are less than current
     *         point coordinates
     */
    public boolean contains(Point point) {
        if (point == null) {
            return false;
        }
        return Math.abs(point.x) <= Math.abs(this.x) && Math.abs(point.y) <= Math.abs(this.y);
    }

    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
