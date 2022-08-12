package tetramap.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import tetramap.config.MapConfig;
import tetramap.config.ScaleControlConfig;
import tetramap.config.ZoomControlConfig;
import tetramap.draw.CircleDrawAdapter;
import tetramap.draw.CircleDrawAdapterLeaflet;
import tetramap.draw.MarkerDrawAdapter;
import tetramap.draw.MarkerDrawAdapterLeaflet;
import tetramap.marker.MarkerManager;
import tetramap.marker.MarkerManagerLeaflet;
import tetramap.entity.LatLong;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapMoveEventManager;
import tetramap.layer.MapLayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Карта от Leaflet для JavaFX
 */
public class MapViewLeaflet extends StackPane implements MapView {
    // Контейнер для html карты
    private final WebView webView = new WebView();
    private final WebEngine webEngine;

    // Менеджер на нажатие мыши
    private final MapClickEventManager mapClickEventManager;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEventManager;

    // Draw адаптеры для рисования фигур
    private final CircleDrawAdapter circleDrawAdapter;
    private final MarkerDrawAdapter markerDrawAdapter;

    public MapViewLeaflet() {
        this.webEngine = this.webView.getEngine();
        this.getChildren().add(this.webView);
        mapClickEventManager = new MapClickEventManager();
        mapMoveEventManager = new MapMoveEventManager();
        circleDrawAdapter = new CircleDrawAdapterLeaflet(this);
        markerDrawAdapter = new MarkerDrawAdapterLeaflet(this);
    }

    @Override
    public CompletableFuture<Worker.State> displayMap(MapConfig mapConfig) {
        CompletableFuture<Worker.State> finalMapLoadState = new CompletableFuture<>();
        this.webEngine.getLoadWorker().stateProperty().addListener((new ChangeListener() {
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
        this.webEngine.load(urlWeb.toExternalForm());
        return finalMapLoadState;
    }

    private void executeMapSetupScripts(MapConfig mapConfig) {
        StringBuilder stringBuilder;
        Iterator<MapLayer> iterator;
        int index;

        // Настройки Layers
        List<MapLayer> configLayers = mapConfig.getLayers();
        iterator = configLayers.iterator();
        index = 0;
        while(iterator.hasNext()) {
            MapLayer layer = iterator.next();
            stringBuilder = (new StringBuilder()).append("var layer").append(++index).append(" = ");
            execScript(stringBuilder.append(layer.getJavaScriptCode()).append(';').toString());
        }
        configLayers = mapConfig.getLayers();

        Iterable<MapLayer> iterable = configLayers;
        Collection<String> destinationList = new ArrayList<>();
        index = 0;
        iterator = iterable.iterator();
        while(iterator.hasNext()) {
            MapLayer layer = iterator.next();
            StringBuilder sb = (new StringBuilder()).append('\'');
            destinationList.add(sb.append(layer.getDisplayName()).append("': layer").append(++index).toString());
        }
        StringBuffer jsLayers = new StringBuffer();
        destinationList.forEach(elem -> jsLayers.append(elem).append(","));
        execScript("var baseMaps = { " + jsLayers + " };");

        // Установка центра карты, зума, отображения 1 слоя
        LatLong latLng = mapConfig.getInitialCenter();
        stringBuilder = (new StringBuilder()).append("var map = L.map('map', {center: new L.LatLng(");
        stringBuilder.append(latLng.getLatitude()).append(", ")
                .append(latLng.getLongitude()).append("),zoom: 14,zoomControl: false,layers: [layer1]});")
                .append("var attribution = map.attributionControl;attribution.setPrefix('Leaflet');");
        execScript(stringBuilder.toString());
        if (mapConfig.getLayers().size() > 1) {
            execScript("var overlayMaps = {};L.control.layers(baseMaps, overlayMaps).addTo(map);");
        }

        // Настройки масштаба
        ScaleControlConfig scaleControlConfig = mapConfig.getScaleControlConfig();
        if (scaleControlConfig.isShow()) {
            stringBuilder = (new StringBuilder()).append("L.control.scale({position: '");
            stringBuilder.append(scaleControlConfig.getPosition().getPositionName()).append("', ").append("metric: ");
            stringBuilder.append(scaleControlConfig.isMetric()).append(", ").append("imperial: ");
            execScript(stringBuilder.append(!scaleControlConfig.isMetric()).append("})").append(".addTo(map);").toString());
        }

        // Настройки Zoom
        ZoomControlConfig zoomControlConfig = mapConfig.getZoomControlConfig();
        if (zoomControlConfig.isShow()) {
            stringBuilder = (new StringBuilder()).append("L.control.zoom({position: '");
            execScript(stringBuilder.append(zoomControlConfig.getPosition().getPositionName()).append("'})").append(".addTo(map);").toString());
        }

        // Установка слушателей на мышь
        addMouseClickEvent();
        addMouseMoveEvent();
    }

    public Object execScript(String script) {
        return this.webEngine.executeScript(script);
    }

    @Override
    public void setSize(double width, double height) {
        webView.setPrefSize(width, height);
    }

    /**
     * Реализация события вызова метода mapMove() при перемещении мыши
     */
    public void addMouseMoveEvent() {
        Object document = this.execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript("map.on('mousemove', function(e){ document.java.mapMove(e.latlng.lat, e.latlng.lng);});");
        }
    }

    @Override
    public void addMouseMoveListener(MapMoveEventListener mapMoveEventListener) {
        mapMoveEventManager.addListener(mapMoveEventListener);
    }

    @Override
    public void removeMouseMoveListener(MapMoveEventListener mapMoveEventListener) {
        mapMoveEventManager.removeListener(mapMoveEventListener);
    }

    /**
     * Вызов метода mapMoveEvent у каждого слушателя для определенного LatLong
     * @param lat широта
     * @param lng долгота
     */
    public void mapMove(double lat, double lng) {
        LatLong latlng = new LatLong(lat, lng);
        mapMoveEventManager.mapMoveEvent(latlng);
    }

    /**
     * Реализация события вызова метода mapClick() при нажатии мыши
     */
    public void addMouseClickEvent() {
        Object document = this.execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript("map.on('click', function(e){ document.java.mapClick(e.latlng.lat, e.latlng.lng);});");
        }
    }

    @Override
    public void addMouseClickListener(MapClickEventListener mapClickEventListener) {
        mapClickEventManager.addListener(mapClickEventListener);
    }

    @Override
    public void removeMouseClickListener(MapClickEventListener mapClickEventListener) {
        mapClickEventManager.removeListener(mapClickEventListener);
    }

    /**
     * Вызов метода mapClickEvent у каждого слушателя для определенного LatLong
     * @param lat широта
     * @param lng долгота
     */
    public void mapClick(double lat, double lng) {
        System.out.println(lat + " " + lng);
        LatLong latlng = new LatLong(lat, lng);
        mapClickEventManager.mapClickEvent(latlng);
    }

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
    public CircleDrawAdapter getCircleDrawAdapter() {
        return circleDrawAdapter;
    }

    @Override
    public MarkerDrawAdapter getMarkerDrawAdapter() {
        return markerDrawAdapter;
    }
}
