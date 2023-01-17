package tetramap.draw;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.PolylineDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.gui.MapView;

@Log4j2
@Getter
public class PolylineDrawAdapterImpl implements PolylineDrawAdapter {

    private final MapView mapView;

    private final Polyline polyline;

    public PolylineDrawAdapterImpl(MapView mapView) {
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

        // Добавляем новую координату и обновляем polyline
        ((LatLongArray)polyline.getLatLongs()).add(latLong);
        polyline.updateTo();
    }
}