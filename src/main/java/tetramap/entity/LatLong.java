package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;

/**
 * Географическая координата (широта, долгота и высота).
 */
@Data
@AllArgsConstructor
public class LatLong {

    /**
     * Широта
     */
    private double latitude;

    /**
     * Долгота
     */
    private double longitude;
}
