package tetramap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetramap.config.*;
import tetramap.entity.LatLong;
import tetramap.gui.MapPane;
import tetramap.gui.MapView;
import tetramap.gui.MapViewLeaflet;
import tetramap.layer.MapLayer;

import java.util.List;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        // Загрузка карты
        MapConfig mapConfig = new MapConfig(
                List.of(MapLayer.OPENSTREETMAP,
                        MapLayer.OPENCYCLEMAP,
                        MapLayer.YANDEXMAP,
                        MapLayer.GISMAP,
                        MapLayer.WORLDSTREETMAP,
                        MapLayer.GOOGLEMAP),
                new ZoomControlConfig(true, ControlPosition.BOTTOM_LEFT),
                new ScaleControlConfig(true, ControlPosition.BOTTOM_LEFT,true),
                new LatLong(55.030, 73.2695)
        );
        MapView mapView = new MapViewLeaflet();
        mapView.displayMap(mapConfig);

        MapPane mapPane = new MapPane(mapView);
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

