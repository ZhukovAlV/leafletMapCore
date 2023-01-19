package tetramap.event;

import tetramap.entity.marker.Marker;

import java.util.List;

public class MarkerEventListenerImpl implements MarkerEventListener {

    @Override
    public void onMarkerSelected(Marker marker, boolean selectedState) {
        System.out.println(marker + " : " + selectedState);
    }

    @Override
    public void onMarkersSelected(List<Marker> markers, boolean selectedState) {
        System.out.println(markers + " : " + selectedState);
    }
}
