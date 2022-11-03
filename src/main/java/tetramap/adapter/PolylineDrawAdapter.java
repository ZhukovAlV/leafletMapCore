package tetramap.adapter;

import lombok.Getter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.event.MapClickEventListener;
import tetramap.gui.MapView;

@Getter
public class PolylineDrawAdapter implements Invokable, MapClickEventListener {

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

        mapView.addMouseClickListener(this);
    }

    @Override
    public void onRevoke() {
        mapView.removeMouseClickListener(this);
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        ((LatLongArray)polyline.getLatLongs()).add(latLong);
    }
}