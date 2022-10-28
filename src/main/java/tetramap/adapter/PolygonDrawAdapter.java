package tetramap.adapter;

import tetramap.gui.MapView;

/**
 * Обработчик для рисования области выделения полигоном (многоугольником) на карте
 */
public class PolygonDrawAdapter implements Invokable {

    private final MapView mapView;

    public PolygonDrawAdapter(MapView mapView) {
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
}
