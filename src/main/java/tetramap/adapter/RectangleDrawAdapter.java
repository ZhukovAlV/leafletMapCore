package tetramap.adapter;

import tetramap.entity.vectors.Rectangle;
import tetramap.event.MapLeftClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.gui.MapView;

public interface RectangleDrawAdapter extends Invokable, MapMoveEventListener, MapLeftClickEventListener {

    MapView getMapView();
    Rectangle getRectangle();
}