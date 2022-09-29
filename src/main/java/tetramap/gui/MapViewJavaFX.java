package tetramap.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import lombok.extern.log4j.Log4j2;
import tetramap.config.MapConfig;
import tetramap.entity.BaseMaps;
import tetramap.entity.TileLayer;
import tetramap.entity.control.LayersControl;
import tetramap.entity.control.ScaleControl;
import tetramap.entity.control.ZoomControl;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapMoveEventManager;
import tetramap.layer.Layer;
import tetramap.leaflet.LeafletMap;

import java.net.URL;
import java.util.List;
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

    // Менеджер на нажатие мыши
    private final MapClickEventManager mapClickEventManager;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEventManager;

    public MapViewJavaFX() {
        getChildren().add(WEB_VIEW);

        mapClickEventManager = new MapClickEventManager();
        mapMoveEventManager = new MapMoveEventManager();
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
        StringBuilder stringBuilder;

        // Создаем тайловые слои для карты
        List<TileLayer> tileLayerList = mapConfig.getLayers();
        tileLayerList.forEach(tileLayer -> tileLayer.createTo(this));

        BaseMaps baseMaps = new BaseMaps(tileLayerList);
        baseMaps.createTo(this);

        // Создаем карту (map здесь это div контейнер)
        map = mapConfig.getMap();
        map.createTo(this);

        // Создаем меню выбора тайловых слоев
        LayersControl layersControl = new LayersControl(baseMaps);
        layersControl.createTo(this);
        layersControl.addTo(this);

        // Настройки масштаба
        ScaleControl scaleControl = mapConfig.getScaleControl();
        if (scaleControl.isShow()) {
            scaleControl.createTo(this);
            scaleControl.addTo(this);
        }

        // Настройки Zoom
        ZoomControl zoomControl = mapConfig.getZoomControl();
        if (zoomControl.isShow()) {
            zoomControl.createTo(this);
            zoomControl.addTo(this);
        }
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
    public LeafletMap getMap() {
        return map;
    }

    /**
     * Реализация события вызова метода mapMove() при перемещении мыши
     */
/*    public void addMouseMoveEvent() {
        Object document = leafletMap.execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            leafletMap.execScript("map.on('mousemove', function(e){ document.java.mapMove(e.latlng.lat, e.latlng.lng);});");
        }
    }*/

    /**
     * Вызов метода mapMoveEvent у каждого слушателя для определенного LatLong
     * @param lat широта
     * @param lng долгота
     */
/*    public void mapMove(double lat, double lng) {
        LatLong latlng = new LatLong(lat, lng);
        mapMoveEventManager.mapMoveEvent(latlng);
    }*/

    /**
     * Реализация события вызова метода mapClick() при нажатии мыши
     */
/*    public void addMouseClickEvent() {
        Object document = leafletMap.execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            leafletMap.execScript("map.on('click', function(e){ document.java.mapClick(e.latlng.lat, e.latlng.lng);});");
        }
    }*/

    /**
     * Вызов метода mapClickEvent у каждого слушателя для определенного LatLong
     */
/*    public void mapClick(double lat, double lng) {
        System.out.println(lat + " " + lng);
        LatLong latlng = new LatLong(lat, lng);
        mapClickEventManager.mapClickEvent(latlng);
    }*/

    /*    public void addTrack() {
        Collection destination = new ArrayList();
        destination.add("    [" + 55.030 + ", " + 73.2695 + ']');
        destination.add("    [" + 55.130 + ", " + 73.3695 + ']');

        StringBuffer jsPositions = new StringBuffer();
        destination.forEach(elem -> jsPositions.append(elem).append(", \n"));

        String script = "var latLngs = [" + jsPositions + "]; var polyline = L.polyline(latLngs, {color: 'red', weight: 2}).addTo(map); map.fitBounds(polyline.getBounds());";
        this.execScript(script);
    }*/

    @Override
    public void addLayer(Layer layer) {
        log.info("Добавление layer: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        execScript(String.join("",layer.getId(), ".addTo(", map.getId(), ");"));
    }

    @Override
    public void removeLayer(Layer layer) {
        log.info("Удаление layer: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        execScript(String.join("",layer.getId(), ".removeFrom(", map.getId(), ");"));
    }

    @Override
    public void remove() {
        log.info("Удаление карты со всеми слоями: {}", "id: " + map.getId());
        // TODO доделать метод
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
    public boolean hasLayer(Layer layer) {
        log.info("Проверка layer на exist: {}", String.join("",layer.getLeafletType(), ", id: ", layer.getId()));
        // TODO доделать метод
        return false;
    }
}
