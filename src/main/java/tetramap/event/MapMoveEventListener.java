package tetramap.event;

import org.mapsforge.core.model.LatLong;

public interface MapMoveEventListener {
    void onMapMove(LatLong latLong);
}
