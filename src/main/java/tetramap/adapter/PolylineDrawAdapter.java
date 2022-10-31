package tetramap.adapter;

import tetramap.gui.MapView;

public class PolylineDrawAdapter implements Invokable {

    private final MapView mapView;

    public PolylineDrawAdapter(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void onInvoke() {
        // enable polygon Draw Mode
        mapView.execScript(mapView.getMap().getId() + ".pm.enableDraw('Line', {\n" +
                "  snappable: true,\n" +
                "  snapDistance: 20,\n" +
                "});");
    }

    @Override
    public void onRevoke() {
        mapView.execScript(mapView.getMap().getId() + ".pm.disableDraw();");
    }
}