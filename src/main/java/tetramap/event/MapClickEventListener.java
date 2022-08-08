package tetramap.event;

import org.mapsforge.core.model.LatLong;

public interface MapClickEventListener {
    void onMapClick(LatLong latLong);
}
