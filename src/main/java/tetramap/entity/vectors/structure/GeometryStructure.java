package tetramap.entity.vectors.structure;

import tetramap.entity.LatLongBounds;

/**
 * Core interface to geometry structures
 */
public interface GeometryStructure {

    /**
     * Returns true if the geometry structure has no LatLngs.
     *
     * @return return true if this geometry structure has no coordinates
     *         otherwise return false.
     */
    boolean isEmpty();

    /**
     * Calculate the boundary of this geometry structure
     *
     * @return the {@link LatLongBounds} of the geometry
     */
    LatLongBounds getBounds();
}