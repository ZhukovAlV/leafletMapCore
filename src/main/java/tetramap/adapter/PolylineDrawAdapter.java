package tetramap.adapter;

import lombok.Getter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.event.MapLeftClickEventListener;
import tetramap.gui.MapView;

@Getter
public class PolylineDrawAdapter implements Invokable, MapLeftClickEventListener {

    private final MapView mapView;

    private final Polyline polyline;

    public PolylineDrawAdapter(MapView mapView) {
        this.mapView = mapView;
        this.polyline = new Polyline();
    }

    @Override
    public void onInvoke() {
        // Добавляем объект на карту
        getMapView().getLayerGroup().addLayer(polyline);

        mapView.addLeftMouseClickListener(this);
    }

    @Override
    public void onRevoke() {
        mapView.removeLeftMouseClickListener(this);
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        ((LatLongArray)polyline.getLatLongs()).add(latLong);
    }
}