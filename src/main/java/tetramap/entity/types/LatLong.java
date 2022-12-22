package tetramap.entity.types;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Географическая координата (широта, долгота и высота).
 */
@Data
@AllArgsConstructor
public class LatLong implements Serializable {

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
        return "[" + latitude +", " + longitude + "]";
    }
}