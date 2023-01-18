package tetramap.adapter;

import tetramap.event.MapLeftClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapRightClickEventListener;

public interface PolygonDrawAdapter extends Invokable, MapMoveEventListener, MapLeftClickEventListener, MapRightClickEventListener {
}
