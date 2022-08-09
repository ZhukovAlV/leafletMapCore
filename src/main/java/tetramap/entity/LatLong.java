package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Географическая координата (широта и долгота).
 */
@Data
@AllArgsConstructor
public class LatLong {
    private double latitude;
    private double longitude;
}
