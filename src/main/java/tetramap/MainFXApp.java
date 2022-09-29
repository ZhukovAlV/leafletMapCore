package tetramap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetramap.config.MapConfig;
import tetramap.entity.Attribution;
import tetramap.entity.TileLayer;
import tetramap.entity.control.ScaleControl;
import tetramap.entity.control.ZoomControl;
import tetramap.entity.types.LatLong;
import tetramap.gui.MapPaneJavaFX;
import tetramap.gui.MapView;
import tetramap.gui.MapViewJavaFX;
import tetramap.leaflet.LeafletControl;
import tetramap.leaflet.LeafletMap;

import java.util.ArrayList;
import java.util.List;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    @Override
    public void start(Stage primaryStage) {

        // Создаем список тайловых слоев
        List<TileLayer> layers = new ArrayList<>();
        layers.add(new TileLayer("OpenStreetMap"
                , "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                ,"OpenStreetMap"
                , false
                ,"abc"
                ,0
                ,18));
        layers.add(new TileLayer("YandexMap"
                , "http://sat0{s}.maps.yandex.net/tiles?l=sat&x={x}&y={y}&z={z}"
                ,"YandexMap"
                , true
                ,"123"
                ,0
                ,18));
        layers.add(new TileLayer("2GIS"
                , "http://tile{s}.maps.2gis.com/tiles?x={x}&y={y}&z={z}"
                ,"2GIS"
                , false
                ,"0123"
                ,0
                ,18));

        // Установка значения (названия) префикса внизу карты справа (по умолчанию пусто)
        Attribution attribution = new Attribution();

        // Создаем настройки для карты
        LeafletMap map  = new LeafletMap("map", new LatLong(55.030, 73.2695),
                14, 0, 18,false, layers.get(0), attribution);


        // Добавляем это все в конфигурационный файл
        MapConfig mapConfig = new MapConfig(
                layers,
                new ZoomControl(false, LeafletControl.ControlPosition.bottomleft),
                new ScaleControl(true, LeafletControl.ControlPosition.bottomleft,true),
                map
        );

        MapView mapView = new MapViewJavaFX();
        mapView.displayMap(mapConfig);
        /* mapView.addMouseClickEvent();
                mapView.addMouseMoveEvent();*/


        MapPaneJavaFX mapPaneJavaFX = new MapPaneJavaFX(mapView);
        mapPaneJavaFX.initialize();

        Scene scene = new Scene(mapPaneJavaFX, WIGHT, HIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TetraMap in JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

