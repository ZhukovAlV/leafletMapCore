package tetramap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetramap.config.*;
import tetramap.entity.*;
import tetramap.gui.MapPane;
import tetramap.gui.MapView;
import tetramap.gui.MapViewLeaflet;
import tetramap.layer.MapLayerEnum;

import java.util.List;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        // Загрузка карты
        MapConfig mapConfig = new MapConfig(
                List.of(MapLayerEnum.OPENSTREETMAP,
                        MapLayerEnum.OPENCYCLEMAP,
                        MapLayerEnum.YANDEXMAP,
                        MapLayerEnum.GISMAP,
                        MapLayerEnum.WORLDSTREETMAP,
                        MapLayerEnum.GOOGLEMAP),
                new ZoomControlConfig(true, ControlPosition.BOTTOM_LEFT),
                new ScaleControlConfig(true, ControlPosition.BOTTOM_LEFT,true),
                new LatLong(55.030, 73.2695)
        );
        MapView mapView = new MapViewLeaflet();
        mapView.displayMap(mapConfig);

        // Добавим маркер
/*        LatLong latLong = new LatLong(55.030, 73.2695);
        Icon icon = new IconLeaflet(getClass().getResource("../markerIcon/subscriber/marker_green.png").getPath());
        mapView.execScript("var myIcon = L.icon({iconUrl: '" + icon.getIconUrl() + "', iconSize: [24, 24], iconAnchor: [12, 12], });");
        mapView.execScript("L.marker([" + latLong.getLatitude() + "," + latLong.getLongitude() + "], {icon: myIcon}).addTo(map);");*/

        LatLong latLong = new LatLong(55.030, 73.2695);
        Icon icon = new IconLeaflet(getClass().getResource("../markerIcon/subscriber/marker_green.png").getPath());
        MarkerLeaflet marker = new MarkerLeaflet(latLong, icon);
        mapView.addTo(marker);


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

