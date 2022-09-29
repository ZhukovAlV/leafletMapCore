package tetramap.entity.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a rectangular geographical area on a map.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LatLongBounds implements Serializable {

    @Serial
    private static final long serialVersionUID = -7421430446913242834L;

    private LatLong southWest;
    private LatLong northEast;

    public LatLongBounds(LatLong... latlngs) {
        this(Arrays.asList(latlngs));
    }

    public LatLongBounds(List<LatLong> latlngs) {
        extend(latlngs);
    }

    /**
     * Returns the west longitude of the bounds
     *
     * @return the west longitude of the bounds
     */
    public double getWest() {
        return this.southWest.getLongitude();
    }

    /**
     * Returns the south latitude of the bounds
     *
     * @return the south latitude of the bounds
     */
    public double getSouth() {
        return this.southWest.getLatitude();
    }

    /**
     * Returns the east longitude of the bounds
     *
     * @return the east longitude of the bounds
     */
    public double getEast() {
        return this.northEast.getLongitude();
    }

    /**
     * Returns the north latitude of the bounds
     *
     * @return the north latitude of the bounds
     */
    public double getNorth() {
        return this.northEast.getLatitude();
    }

    /**
     * Returns the center point of the bounds.
     *
     * @return the center point of the bounds.
     */
    public LatLong getCenter() {
        double lat = (this.southWest.getLatitude() + this.northEast.getLatitude()) / 2;
        double lon = (this.southWest.getLongitude() + this.northEast.getLongitude()) / 2;
        return new LatLong(lat, lon);
    }

    public void extend(LatLong... LatLongs) {
        extend(Arrays.asList(LatLongs));
    }

    public void extend(List<LatLong> LatLongs) {
        if (LatLongs != null && !LatLongs.isEmpty()) {
            LatLong LatLong = LatLongs.get(0);
            if (northEast == null) {
                northEast = new LatLong(LatLong.getLatitude(), LatLong.getLongitude());
            }
            if (southWest == null) {
                southWest = new LatLong(LatLong.getLatitude(), LatLong.getLongitude());
            }
            for (LatLong latLong : LatLongs) {
                northEast.setLatitude(Math.max(northEast.getLatitude(), latLong.getLatitude()));
                northEast.setLongitude(Math.max(northEast.getLongitude(), latLong.getLongitude()));
                southWest.setLatitude(Math.min(southWest.getLatitude(), latLong.getLatitude()));
                southWest.setLongitude(Math.min(southWest.getLongitude(), latLong.getLongitude()));
            }
        }
    }

    /**
     * Extend the bounds to contain the given bounds
     *
     * @param bounds
     *            the bounds
     */
    public void extend(LatLongBounds bounds) {
        if (bounds != null) {
            if (northEast == null) {
                northEast = new LatLong(bounds.getNorth(), bounds.getEast());
            }
            if (southWest == null) {
                southWest = new LatLong(bounds.getSouth(), bounds.getWest());
            }
            northEast.setLatitude(Math.max(northEast.getLatitude(), bounds.getSouthWest().getLatitude()));
            northEast.setLongitude(Math.max(northEast.getLongitude(), bounds.getSouthWest().getLongitude()));
            southWest.setLatitude(Math.min(southWest.getLatitude(), bounds.getNorthEast().getLatitude()));
            southWest.setLongitude(Math.min(southWest.getLongitude(), bounds.getNorthEast().getLongitude()));
        }
    }
}
