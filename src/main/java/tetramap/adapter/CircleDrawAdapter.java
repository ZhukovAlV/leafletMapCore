package tetramap.adapter;

import tetramap.entity.vectors.Circle;
import tetramap.entity.vectors.CircleMarker;
import tetramap.event.MapLeftClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.gui.MapView;

public interface CircleDrawAdapter extends Invokable, MapMoveEventListener, MapLeftClickEventListener {
    MapView getMapView();
    Circle getCircle();
    void setCircle(Circle circle);
}