package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Географическая координата (широта, долгота и высота).
 */
@Data
@AllArgsConstructor
public class LatLong implements Serializable {

    @Serial
    private static final long serialVersionUID = 8519525431224154852L;

    /**
     * Широта
     */
    private double latitude;

    /**
     * Долгота
     */
    private double longitude;

    @Override
    public String toString() {
        return "[" + latitude +", " + longitude + ']';
    }
}