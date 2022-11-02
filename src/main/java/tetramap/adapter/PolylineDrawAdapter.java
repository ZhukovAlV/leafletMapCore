package tetramap.adapter;

import lombok.Getter;
import tetramap.entity.types.LatLong;
import tetramap.event.MapClickEventListener;
import tetramap.gui.MapView;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PolylineDrawAdapter implements Invokable, MapClickEventListener {

    private final MapView mapView;

    private final List<LatLong> listLatLong = new ArrayList<>();

    public PolylineDrawAdapter(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void onInvoke() {
        // Включаем режим polygon Draw Mode
        mapView.execScript(mapView.getMap().getId() + ".pm.enableDraw('Line', {\n" +
                "  snappable: true,\n" +
                "  snapDistance: 20,\n" +
                "  tooltips: false,\n" +
                "});");

        mapView.addMouseClickListener(this);
    }

    @Override
    public void onRevoke() {
        // Выключаем режим polygon Draw Mode
        mapView.execScript(mapView.getMap().getId() + ".pm.disableDraw();");

        mapView.removeMouseClickListener(this);
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        listLatLong.add(latLong);
    }
}