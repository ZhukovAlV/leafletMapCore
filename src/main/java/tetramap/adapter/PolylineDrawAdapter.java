package tetramap.adapter;

import tetramap.entity.vectors.Polyline;
import tetramap.event.MapLeftClickEventListener;
import tetramap.gui.MapView;

public interface PolylineDrawAdapter extends Invokable, MapLeftClickEventListener {
    MapView getMapView();
    Polyline getPolyline();
}
