package tetramap.gui;

import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import tetramap.config.MapConfig;
import tetramap.draw.CircleDrawAdapter;
import tetramap.draw.CircleDrawAdapterLeaflet;
import tetramap.draw.MarkerDrawAdapter;
import tetramap.draw.MarkerDrawAdapterLeaflet;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapMoveEventManager;
import tetramap.layer.Layer;
import tetramap.layer.MapLayerLeaflet;

import java.util.concurrent.CompletableFuture;

/**
 * Карта от Leaflet для JavaFX
 */
public class MapViewLeaflet extends StackPane implements MapView {

    // Карта
    private final MapLayerLeaflet mapLayer = new MapLayerLeaflet();

    // Менеджер на нажатие мыши
    private final MapClickEventManager mapClickEventManager;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEventManager;

    // Draw адаптеры для рисования фигур
    private final CircleDrawAdapter circleDrawAdapter;
    private final MarkerDrawAdapter markerDrawAdapter;

    public MapViewLeaflet() {
        getChildren().add(mapLayer.getWebView());
        mapClickEventManager = new MapClickEventManager();
        mapMoveEventManager = new MapMoveEventManager();
        circleDrawAdapter = new CircleDrawAdapterLeaflet(this);
        markerDrawAdapter = new MarkerDrawAdapterLeaflet(this);
    }

    @Override
    public void setSize(double width, double height) {
        mapLayer.getWebView().setPrefSize(width, height);
    }

    @Override
    public void addMouseMoveListener(MapMoveEventListener mapMoveEventListener) {
        mapMoveEventManager.addListener(mapMoveEventListener);
    }

    @Override
    public void removeMouseMoveListener(MapMoveEventListener mapMoveEventListener) {
        mapMoveEventManager.removeListener(mapMoveEventListener);
    }

    @Override
    public void addMouseClickListener(MapClickEventListener mapClickEventListener) {
        mapClickEventManager.addListener(mapClickEventListener);
    }

    @Override
    public void removeMouseClickListener(MapClickEventListener mapClickEventListener) {
        mapClickEventManager.removeListener(mapClickEventListener);
    }

    @Override
    public CircleDrawAdapter getCircleDrawAdapter() {
        return circleDrawAdapter;
    }

    @Override
    public MarkerDrawAdapter getMarkerDrawAdapter() {
        return markerDrawAdapter;
    }

    @Override
    public void addTo(Layer layer) {
        mapLayer.addTo(layer);
    }

    @Override
    public void execScript(String script) {
        mapLayer.execScript(script);
    }

    @Override
    public CompletableFuture<Worker.State> displayMap(MapConfig mapConfig) {
        return mapLayer.displayMap(mapConfig);
    }
}
