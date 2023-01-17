package tetramap.adapter;

import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.decorator.PolylineDecorator;
import tetramap.event.MapLeftClickEventListener;
import tetramap.gui.MapView;

public interface RouteDrawAdapter extends Invokable, MapLeftClickEventListener {

    MapView getMapView();
    Polyline getLatLongPolyline();
    PolylineDecorator getPolylineDecorator();
}
