package tetramap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetramap.config.*;
import tetramap.entity.LatLng;
import tetramap.gui.MapPaneFX;
import tetramap.gui.MapView;
import tetramap.gui.MapViewLeafletFX;
import tetramap.layer.MapLayer;

import java.util.List;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        // Загрузка карты
        MapConfig mapConfig = new MapConfigLeaflet(
                List.of(MapLayer.OPENSTREETMAP, MapLayer.OPENCYCLEMAP),
                new ZoomControlConfig(true, ControlPosition.BOTTOM_LEFT),
                new ScaleControlConfig(true, ControlPosition.BOTTOM_LEFT,true),
                new LatLng(55.030, 73.2695)
        );
        MapView mapView = new MapViewLeafletFX();
        mapView.displayMap(mapConfig);

        MapPaneFX mapPane = new MapPaneFX(mapView);
        mapPane.initialize();

        Scene scene = new Scene(mapPane, WIGHT, HIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TetraMap in JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

