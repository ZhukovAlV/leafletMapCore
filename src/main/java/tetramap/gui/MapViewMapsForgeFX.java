package tetramap.gui;

import javafx.concurrent.Worker;
import tetramap.config.MapConfig;

import java.util.concurrent.CompletableFuture;

public class MapViewMapsForgeFX implements MapView {
    @Override
    public CompletableFuture<Worker.State> displayMap(MapConfig mapConfig) {
        return null;
    }

    @Override
    public void setSize(double width, double height) {

    }

    @Override
    public void onMapClick() {

    }

    @Override
    public void onMapMove() {

    }
}
