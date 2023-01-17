package tetramap.draw;

import tetramap.adapter.PolygonDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.gui.MapView;

/**
 * Обработчик для рисования области выделения полигоном (многоугольником) на карте
 */
public class PolygonDrawAdapterImpl implements PolygonDrawAdapter {

    private final MapView mapView;

    public PolygonDrawAdapterImpl(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void onInvoke() {
        // enable polygon Draw Mode
        mapView.execScript(mapView.getMap().getId() + ".pm.enableDraw('Polygon', {\n" +
                "  snappable: true,\n" +
                "  snapDistance: 20,\n" +
                "});");
    }

    @Override
    public void onRevoke() {
        mapView.execScript(mapView.getMap().getId() + ".pm.disableDraw();");
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {
        // TODO доделать PolygonDrawAdapter
    }
}
