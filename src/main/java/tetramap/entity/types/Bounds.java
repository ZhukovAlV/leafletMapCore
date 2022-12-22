package tetramap.entity.types;

import lombok.Data;

import java.io.Serializable;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a rectangular area in pixel coordinates.
 */
@Data
public class Bounds implements Serializable {

    private static final long serialVersionUID = 181166727523469617L;

    private Point max = new Point();
    private Point min = new Point();

    public Bounds() {
        max.setX(Double.MAX_VALUE);
        max.setY(Double.MAX_VALUE);
        min.setX(Double.NEGATIVE_INFINITY);
        min.setY(Double.NEGATIVE_INFINITY);
    }

    public Bounds(Point... point) {
        this();
        extend(point);
    }

    /**
     * Returns the bottom-left point of the bounds.
     *
     * @return the bottom-left point of the bounds.
     */
    public Point getBottomLeft() {
        return new Point(this.min.getX(), this.max.getY());
    }

    /**
     * Returns the bottom-left point of the bounds.
     *
     * @return the bottom-left point of the bounds.
     */
    public Point getTopRight() {
        return new Point(this.max.getX(), this.min.getY());
    }

    /**
     * Returns the top-left point of the bounds
     *
     * @return the top-left point of the bounds
     */
    public Point getTopLeft() {
        return this.min;
    }

    /**
     * Returns the bottom-right point of the bounds
     *
     * @return the bottom-right point of the bounds
     */
    public Point getBottomRight() {
        return this.max;
    }

    public void extend(Point... points) {
        for (Point p : points) {
            extend(p.getX(), p.getY());
        }
    }

    public void extend(double x, double y) {
        max.setX(max(max.getX(), x));
        max.setY(max(max.getY(), y));
        min.setX(min(min.getX(), x));
        min.setY(min(min.getY(), y));
    }

    public Point getCenter() {
        double x = (this.min.getX() + this.max.getY()) / 2;
        double y = (this.min.getX() + this.max.getY()) / 2;
        return new Point(x, y);
    }
}
