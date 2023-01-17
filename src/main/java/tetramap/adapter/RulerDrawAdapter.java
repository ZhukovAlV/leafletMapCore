package tetramap.adapter;

import tetramap.entity.marker.Marker;
import tetramap.entity.vectors.Polyline;
import tetramap.event.MapLeftClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapRightClickEventListener;

public interface RulerDrawAdapter extends Invokable, MapLeftClickEventListener, MapMoveEventListener, MapRightClickEventListener {

    int getDistance();
    Marker getMarker();
    Polyline getPolyline();
}
