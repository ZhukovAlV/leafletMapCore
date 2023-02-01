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
import tetramap.entity.types.LatLongBounds;
import tetramap.leaflet.LeafletControl;
import tetramap.leaflet.LeafletMap;

import tetramap.gui.MapPaneJavaFX;
import tetramap.gui.MapViewJavaFX;
import java.util.ArrayList;
import java.util.List;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    public static List<TileLayer> layers = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        // Создаем список тайловых слоев
        layers.add(new TileLayer("OpenStreetMap"
                , "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                ,"OpenStreetMap"
                , false
                ,"abc"
                ,0
                ,18));
        layers.add(new TileLayer("OpenStreetMapLocal"
                , "/home/user/Test/leafletLocal/moscow/{z}/{x}/{y}.png"
                ,"OpenStreetMapLocal"
                , false
                ,""
                ,0
                ,18));
        layers.add(new TileLayer("Google map"
                , "http://mts{s}.google.com/vt/hl=ru&x={x}&y={y}&z={z}"
                ,"Google map"
                , false
                ,"0123"
                ,0
                ,18));
        layers.add(new TileLayer("YandexMap"
                , "https://core-renderer-tiles.maps.yandex.net/tiles?l=map&x={x}&y={y}&z={z}&scale=1&lang=ru_RU"
                ,"YandexMap"
                , true
                ,"1234"
                ,0
                ,18));
        layers.add(new TileLayer("YandexMapSputnik"
                , "http://sat0{s}.maps.yandex.net/tiles?l=sat&x={x}&y={y}&z={z}"
                ,"YandexMapSputnik"
                , true
                ,"1234"
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

        // Координаты для задания зоны видимости карты
        LatLong southWest = new LatLong(53.5925, 70.4223);
        LatLong northEast = new LatLong(58.4477, 76.3769);
        LatLongBounds latLongBounds = new LatLongBounds(southWest, northEast);

        // Добавляем это все в конфигурационный файл
        MapConfig mapConfig = new MapConfig(
                layers,
                new ZoomControl(false, LeafletControl.ControlPosition.bottomleft),
                new ScaleControl(true, LeafletControl.ControlPosition.bottomleft,true),
                map,
                latLongBounds
        );

        MapViewJavaFX mapView = new MapViewJavaFX();
        mapView.displayMap(mapConfig);

        MapPaneJavaFX mapPaneJavaFX = new MapPaneJavaFX(mapView);

        Scene scene = new Scene(mapPaneJavaFX, WIGHT, HIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TetraMap in JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}