package tetramap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetramap.config.*;
import tetramap.entity.*;
import tetramap.entity.control.LAttributionControl;
import tetramap.gui.MapPaneJavaFX;
import tetramap.gui.MapView;
import tetramap.gui.MapViewJavaFX;
import tetramap.type.MapLayerType;

import java.util.ArrayList;
import java.util.List;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    @Override
    public void start(Stage primaryStage) {

        // Создаем список тайловых слоев
        List<LTileLayer> layers = new ArrayList<>();
        layers.add(new LTileLayer("OpenStreetMap"
                , "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                ,"OpenStreetMap"
                , false
                ,"abc"
                ,0
                ,18));
        layers.add(new LTileLayer("YandexMap"
                , "http://sat0{s}.maps.yandex.net/tiles?l=sat&x={x}&y={y}&z={z}"
                ,"YandexMap"
                , true
                ,"123"
                ,0
                ,18));
        layers.add(new LTileLayer("2GIS"
                , "http://tile{s}.maps.2gis.com/tiles?x={x}&y={y}&z={z}"
                ,"2GIS"
                , false
                ,"0123"
                ,0
                ,18));

        // Установка значения (названия) префикса внизу карты справа (по умолчанию пусто)
        LAttributionControl attributionControl = new LAttributionControl();

        // Создаем настройки для карты
        LMap map  = new LMap("map", new LatLong(55.030, 73.2695), 14,
                true, layers.get(0), attributionControl);


        // Добавляем это все в конфигурационный файл
        MapConfig mapConfig = new MapConfig(
                layers,
                new ZoomControlConfig(true, ControlPosition.BOTTOM_LEFT),
                new ScaleControlConfig(true, ControlPosition.BOTTOM_LEFT,true),
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

