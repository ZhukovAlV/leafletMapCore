package tetramap.adapter;

import tetramap.entity.vectors.Polygon;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapRightClickEventListener;

public interface PolygonDrawAdapter extends PolylineDrawAdapter, MapMoveEventListener, MapRightClickEventListener {

    Polygon getPolygon();
}
