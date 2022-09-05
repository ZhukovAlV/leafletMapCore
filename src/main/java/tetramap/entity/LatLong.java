package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;

/**
 * Географическая координата (широта, долгота и высота).
 */
@Data
@AllArgsConstructor
public class LatLong implements BasicType {

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

    /**
     * Высота
     */
  //  private double altitude;
}
