package tetramap.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.extern.log4j.Log4j2;
import tetramap.config.MapConfig;
import tetramap.config.ScaleControlConfig;
import tetramap.config.ZoomControlConfig;
import tetramap.entity.LBaseMaps;
import tetramap.entity.LMap;
import tetramap.entity.LTileLayer;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapMoveEventManager;
import tetramap.layer.Layer;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Карта от Leaflet для JavaFX
 */
@Log4j2
public class MapViewJavaFX extends StackPane implements MapView {

    // Контейнер для html
    private final WebView webView;
    private final WebEngine webEngine;

    // Карта
    private LMap map;

    // Менеджер на нажатие мыши
    private final MapClickEventManager mapClickEventManager;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEventManager;

    public MapViewJavaFX() {
        webView = new WebView();
        webEngine = webView.getEngine();
        getChildren().add(webView);

        mapClickEventManager = new MapClickEventManager();
        mapMoveEventManager = new MapMoveEventManager();
    }

    @Override
    public CompletableFuture<Worker.State> displayMap(MapConfig mapConfig) {
        CompletableFuture<Worker.State> finalMapLoadState = new CompletableFuture<>();
        webEngine.getLoadWorker().stateProperty().addListener((new ChangeListener() {
            public void changed(ObservableValue var1, Object var2, Object var3) {
                this.changed(var1, (Worker.State)var2, (Worker.State)var3);
            }

            public void changed(ObservableValue observableValue, Worker.State workerState, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    executeMapSetupScripts(mapConfig);
                }

                if (newValue == Worker.State.SUCCEEDED || newValue == Worker.State.FAILED) {
                    finalMapLoadState.complete(newValue);
                }

            }
        }));
        URL urlWeb = getClass().getResource("/web/leafletmap.html");
        webEngine.load(urlWeb.toExternalForm());
        return finalMapLoadState;
    }

    private void executeMapSetupScripts(MapConfig mapConfig) {
        StringBuilder stringBuilder;

        // Создаем разные карты в контейнере HTML
        List<LTileLayer> tileLayerList = mapConfig.getLayers();
        tileLayerList.forEach(tileLayer -> tileLayer.createTo(this));

        LBaseMaps baseMaps = new LBaseMaps(tileLayerList);
        baseMaps.createTo(this);

        // Создаем карту (map здесь это div контейнер)
        map = mapConfig.getMap();
        map.createTo(this);

        // execScript("var attribution = " + map.getId() + ".attributionControl;attribution.setPrefix('');");


/*        if (mapConfig.getLayers().size() > 1) {
            execScript("var overlayMaps = {};L.control.layers(" + baseMaps.getId() + ", overlayMaps).addTo(" + map.getId() + ");");
        }*/

        // Настройки масштаба
        ScaleControlConfig scaleControlConfig = mapConfig.getScaleControlConfig();
        if (scaleControlConfig.isShow()) {
            stringBuilder = (new StringBuilder()).append("L.control.scale({position: '");
            stringBuilder.append(scaleControlConfig.getPosition().getPositionName()).append("', ").append("metric: ");
            stringBuilder.append(scaleControlConfig.isMetric()).append(", ").append("imperial: ");
            execScript(stringBuilder.append(!scaleControlConfig.isMetric()).append("})").append(".addTo(" + map.getId() + ");").toString());
        }

        // Настройки Zoom
        ZoomControlConfig zoomControlConfig = mapConfig.getZoomControlConfig();
        if (zoomControlConfig.isShow()) {
            stringBuilder = (new StringBuilder()).append("L.control.zoom({position: '");
            execScript(stringBuilder.append(zoomControlConfig.getPosition().getPositionName()).append("'})").append(".addTo(" + map.getId() + ");").toString());
        }
    }

    @Override
    public void execScript(String script) {
        webEngine.executeScript(script);
    }

    @Override
    public void setSize(double width, double height) {
        webView.setPrefSize(width, height);
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
    public LMap getMap() {
        return map;
    }

    @Override
    public WebView getWebView() {
        return webView;
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
     * @param lat широта
     * @param lng долгота
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
        log.info("add layer: {}", layer);
        execScript(layer.getId() + ".addTo(" + map.getId() + ");");
    }
}
