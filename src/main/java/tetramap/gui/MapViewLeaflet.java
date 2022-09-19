package tetramap.gui;

import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import tetramap.config.MapConfig;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapMoveEventManager;

import java.util.concurrent.CompletableFuture;

/**
 * Карта от Leaflet для JavaFX
 */
public class MapViewLeaflet extends StackPane implements MapView {

    // Карта
    private final LeafletMap mapLayer = new LeafletMap();

    // Менеджер на нажатие мыши
    private final MapClickEventManager mapClickEventManager;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEventManager;

    public MapViewLeaflet() {
        getChildren().add(mapLayer.getWebView());
        mapClickEventManager = new MapClickEventManager();
        mapMoveEventManager = new MapMoveEventManager();
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
    public CompletableFuture<Worker.State> displayMap(MapConfig mapConfig) {
        return mapLayer.displayMap(mapConfig);
    }
}
