package tetramap.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import lombok.extern.log4j.Log4j2;
import netscape.javascript.JSObject;
import tetramap.config.MapConfig;
import tetramap.entity.BaseMaps;
import tetramap.entity.TileLayer;
import tetramap.entity.control.LayersControl;
import tetramap.entity.control.ScaleControl;
import tetramap.entity.control.ZoomControl;
import tetramap.entity.types.LatLong;
import tetramap.entity.types.LatLongBounds;
import tetramap.event.MapLeftClickEventListener;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapRightClickEventListener;
import tetramap.layer.Layer;
import tetramap.layer.groups.LayerGroup;
import tetramap.leaflet.LeafletMap;
import tetramap.manager.*;
import tetramap.route.RouteManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

/**
 * Карта от Leaflet для JavaFX
 */
@Log4j2
public class MapViewJavaFX extends StackPane implements MapView {

    // Контейнер для html
    private final WebView WEB_VIEW = new WebView();

    // Карта
    private LeafletMap map;

    // Все слои на карте
    private final LayerGroup layerGroup;

    // Менеджер на нажатие левой кнопки мыши
    private final MapLeftClickEventManagerImpl mapLeftClickEventListener;

    // Менеджер на нажатие правой кнопки мыши
    private final MapRightClickEventManagerImpl mapRightClickEventListener;

    // Менеджер на перемещение мыши
    private final MapMoveEventManagerImpl mapMoveEventListener;

    // RouteManager для построения маршрута
    private final RouteManager routeManager = new RouteManager();

    // Менеджер маркеров
    private final MarkerManager markerManager;

    // LayersControl со списом слоев для выбора карты
    private LayersControl layersControl;

    public MapViewJavaFX() {
        getChildren().add(WEB_VIEW);

        // Создаем объекты слушателей
        mapLeftClickEventListener = new MapLeftClickEventManagerImpl();
        mapRightClickEventListener = new MapRightClickEventManagerImpl();
        mapMoveEventListener = new MapMoveEventManagerImpl();

        // Инициализируем менеджера маркеров
        markerManager = new MarkerManagerImpl(this);

        layerGroup = new LayerGroup();

        // Загружаем карты для graphhopper в отдельном потоке
        Thread thread = new Thread(){
            public void run(){
                try {
                    Properties props = new Properties();
                    props.load(this.getClass().getResourceAsStream("/project.properties"));
                    File mapForRoute = new File(props.get("route.map").toString());
                    routeManager.initializeData(mapForRoute);
                } catch (IOException e) {
                    log.error("Файл с картой для построения маршрута недоступен");
                }
            }
        };
        thread.start();
    }

    @Override
    public void displayMap(MapConfig mapConfig) {
        CompletableFuture<Worker.State> finalMapLoadState = new CompletableFuture<>();
        WEB_VIEW.getEngine().getLoadWorker().stateProperty().addListener((new ChangeListener() {
            public void changed(ObservableValue var1, Object var2, Object var3) {
                this.changed(var1, (Worker.State)var2, (Worker.State)var3);
            }

            public void changed(ObservableValue observableValue, Worker.State workerState, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    log.info("Выполняется инициализация карты с переданными параметрами.");
                    executeMapSetupScripts(mapConfig);
                }

                if (newValue == Worker.State.SUCCEEDED || newValue == Worker.State.FAILED) {
                    finalMapLoadState.complete(newValue);
                    log.info("Карта успешно загружена.");
                }

            }
        }));
        URL urlWeb = getClass().getResource("/web/leafletmap.html");
        WEB_VIEW.getEngine().load(urlWeb.toExternalForm());
    }

    private void executeMapSetupScripts(MapConfig mapConfig) {
        // Создаем тайловые слои для карты
        List<TileLayer> tileLayerList = mapConfig.getLayers();
        tileLayerList.forEach(tileLayer -> tileLayer.addTo(this));

        BaseMaps baseMaps = new BaseMaps(tileLayerList);
        baseMaps.addTo(this);

        // Создаем карту (map здесь это div контейнер)
        map = mapConfig.getMap();
        map.addTo(this);

        // Создаем меню выбора тайловых слоев
        layersControl = new LayersControl(baseMaps);
        layersControl.addTo(this);

        // Настройки масштаба
        ScaleControl scaleControl = mapConfig.getScaleControl();
        if (scaleControl.isShow()) {
            scaleControl.addTo(this);
        }

        // Настройки Zoom
        ZoomControl zoomControl = mapConfig.getZoomControl();
        if (zoomControl.isShow()) {
            zoomControl.addTo(this);
        }

        // Настройка зоны видимости
        setMaxBounds(mapConfig.getLatLongBounds());

        // Добавляем слушателей в mapView
        addMouseMoveEvent();
        addLeftMouseClickEvent();
        addRightMouseClickEvent();

        // Слушатель на загруженные тайлы
        addTilesLoadEvent();

        // Добавляем layerGroup в mapView
        layerGroup.addTo(this);
    }

    @Override
    public Object execScript(String script) {
        return WEB_VIEW.getEngine().executeScript(script);
    }

    @Override
    public void setSize(double width, double height) {
        WEB_VIEW.setPrefSize(width, height);
    }

    @Override
    public void addMouseMoveListener(MapMoveEventListener mapMoveEventListener) {
        this.mapMoveEventListener.addListener(mapMoveEventListener);
    }

    @Override
    public void removeMouseMoveListener(MapMoveEventListener mapMoveEventListener) {
        this.mapMoveEventListener.removeListener(mapMoveEventListener);
    }

    @Override
    public void addLeftMouseClickListener(MapLeftClickEventListener mapLeftClickEventListener) {
        this.mapLeftClickEventListener.addListener(mapLeftClickEventListener);
    }

    @Override
    public void removeLeftMouseClickListener(MapLeftClickEventListener mapLeftClickEventListener) {
        this.mapLeftClickEventListener.removeListener(mapLeftClickEventListener);
    }

    @Override
    public void addRightMouseClickListener(MapRightClickEventListener mapRightClickEventListener) {
        this.mapRightClickEventListener.addListener(mapRightClickEventListener);
    }

    @Override
    public void removeRightMouseClickListener(MapRightClickEventListener mapRightClickEventListener) {
        this.mapRightClickEventListener.removeListener(mapRightClickEventListener);
    }

    @Override
    public LeafletMap getMap() {
        return map;
    }

    @Override
    public RouteManager getRouteManager() {
        return routeManager;
    }

    public LayersControl getLayersControl() {
        return layersControl;
    }

    /**
     * Реализация события вызова метода mapMove() при перемещении мыши
     */
    public void addMouseMoveEvent() {
        Object document = execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript(getMap().getId() + ".on('mousemove', function(e){ document.java.mapMove(e.latlng.lat, e.latlng.lng);});");
        }
    }

    /**
     * Вызов метода mapMoveEvent у каждого слушателя для определенного LatLong
     * @param lat широта
     * @param lng долгота
     */
    public void mapMove(double lat, double lng) {
        LatLong latlng = new LatLong(lat, lng);
        mapMoveEventListener.mapMoveEvent(latlng);
    }

    /**
     * Реализация события вызова метода mapLeftClick() при нажатии мыши
     */
    public void addLeftMouseClickEvent() {
        Object document = execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript(getMap().getId() + ".on('click', function(e){ document.java.mapLeftClick(e.latlng.lat, e.latlng.lng);});");
        }
    }

    /**
     * Вызов метода mapLeftClickEvent у каждого слушателя для определенного LatLong
     */
    public void mapLeftClick(double lat, double lng) {
        log.info("mapClick: " + lat + " " + lng);
        LatLong latLong = new LatLong(lat, lng);
        mapLeftClickEventListener.mapLeftClickEvent(latLong);
    }

    /**
     * Реализация события вызова метода mapRightClick() при нажатии мыши
     */
    public void addRightMouseClickEvent() {
        Object document = execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript(getMap().getId() + ".on('contextmenu', function(e){ document.java.mapRightClick();});");
        }
    }

    /**
     * Вызов метода нажатия правой кнопки мыши у каждого слушателя
     */
    public void mapRightClick() {
        log.info("Нажата правая кнопка мыши");
        mapRightClickEventListener.mapRightClickEvent();
    }

    /**
     * Реализация события загрузки тайлов
     */
    public void addTilesLoadEvent() {
        Object document = execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript(getMap().getTileLayer().getId() + ".on('tileload', function(e){ document.java.getDataTileLoad(e.coords);});");
        }
    }

    /**
     * Вывод загруженного тайла в логи
     */
    public void getDataTileLoad(Object coord) {
        log.info("Загружен тайл: " + coord + " Zoom(" + getMap().getZoom() + ")");
    }

    @Override
    public void addLayer(Layer layer) {
        log.info("Добавление layer: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        execScript(String.join("",layer.getId(), ".addTo(", map.getId(), ");"));
    }

    @Override
    public void updateLayer(Layer layer) {
        log.info("Добавление обновленного layer на карту: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        execScript(String.join("",layer.getId(), ".addTo(", map.getId(), ");"));
    }

    @Override
    public void removeLayer(Layer layer) {
        log.info("Удаление layer: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        execScript(String.join("",layer.getId(), ".removeFrom(", map.getId(), ");"));
    }

    @Override
    public void remove() {
        log.info("Удаление всех слоев с карты: {}", "id: " + map.getId());
        layerGroup.getLayers().forEach(Layer::remove);
        layerGroup.getLayers().clear();
    }

    @Override
    public void zoomIn() {
        if (map.getMaxZoom() > map.getZoom()) {
            map.setZoom(map.getZoom() + 1);
            log.info("Масштаб карты увеличен до: {}", map.getZoom());
            execScript(map.getId() + ".zoomIn();");
        } else log.error("Уже установлен максимальный масштаб: {}", map.getZoom());
    }

    @Override
    public void zoomOut() {
        if (map.getMinZoom() < map.getZoom()) {
            map.setZoom(map.getZoom() - 1);
            log.info("Масштаб карты уменьшен до: {}", map.getZoom());
            execScript(map.getId() + ".zoomOut();");
        } else log.error("Уже установлен минимальный масштаб: {}", map.getZoom());
    }

    @Override
    public void moveToCenter() {
        log.info("Перемещение карты к центру: {}", map.getCenter());
        execScript(map.getId() + ".panTo(" + map.getCenter() + ");");
    }

    @Override
    public LayerGroup getLayerGroup() {
        return layerGroup;
    }

    @Override
    public void setMaxBounds(LatLongBounds latLongBounds) {
        log.info("Устанавливаются границы карты: {}", latLongBounds);
        execScript(map.getId() + ".setMaxBounds(L.latLngBounds(" + latLongBounds.getSouthWest() + "," + latLongBounds.getNorthEast() +"));");
    }

    @Override
    public boolean hasLayer(Layer layer) {
        log.info("Проверка layer на exist: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        return layerGroup.getLayers().contains(layer);
    }
    @Override
    public MarkerManager getMarkerManager() {
        return markerManager;
    }
}