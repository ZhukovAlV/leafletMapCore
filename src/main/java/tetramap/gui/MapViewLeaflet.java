package tetramap.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.mapsforge.core.model.LatLong;
import tetramap.config.MapConfig;
import tetramap.config.ScaleControlConfig;
import tetramap.config.ZoomControlConfig;
import tetramap.event.MapClickEventManager;
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
    private final MapClickEventManager mapClickEvent;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEvent;

    public MapViewLeaflet() {
        this.webEngine = this.webView.getEngine();
        this.getChildren().add(this.webView);
        mapClickEvent = new MapClickEventManager();
        mapMoveEvent = new MapMoveEventManager();
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
        stringBuilder = (new StringBuilder()).append("var myMap = L.map('map', {center: new L.LatLng(");
        stringBuilder.append(latLng.getLatitude()).append(", ")
                .append(latLng.getLongitude()).append("),zoom: 14,zoomControl: false,layers: [layer1]});")
                .append("var attribution = myMap.attributionControl;attribution.setPrefix('Leaflet');");
        execScript(stringBuilder.toString());
        if (mapConfig.getLayers().size() > 1) {
            execScript("var overlayMaps = {};L.control.layers(baseMaps, overlayMaps).addTo(myMap);");
        }

        // Настройки масштаба
        ScaleControlConfig scaleControlConfig = mapConfig.getScaleControlConfig();
        if (scaleControlConfig.isShow()) {
            stringBuilder = (new StringBuilder()).append("L.control.scale({position: '");
            stringBuilder.append(scaleControlConfig.getPosition().getPositionName()).append("', ").append("metric: ");
            stringBuilder.append(scaleControlConfig.isMetric()).append(", ").append("imperial: ");
            execScript(stringBuilder.append(!scaleControlConfig.isMetric()).append("})").append(".addTo(myMap);").toString());
        }

        // Настройки Zoom
        ZoomControlConfig zoomControlConfig = mapConfig.getZoomControlConfig();
        if (zoomControlConfig.isShow()) {
            stringBuilder = (new StringBuilder()).append("L.control.zoom({position: '");
            execScript(stringBuilder.append(zoomControlConfig.getPosition().getPositionName()).append("'})").append(".addTo(myMap);").toString());
        }
    }

    public Object execScript(String script) {
        return this.webEngine.executeScript(script);
    }

    @Override
    public void setSize(double width, double height) {
        webView.setPrefSize(width, height);
    }

    /**
     * Вызов метода mapMoveEvent у каждого слушателя при перемещении мыши
     */
    @Override
    public void onMapMove() {
        Object document = this.execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript("myMap.on('moveend', function(e){ document.java.mapMove(myMap.getCenter().lat, myMap.getCenter().lng);});");
        }
    }

    /**
     * Вызов метода mapMoveEvent у каждого слушателя для определенного LatLong
     * @param lat широта
     * @param lng долгота
     */
    public void mapMove(double lat, double lng) {
        LatLong latlng = new LatLong(lat, lng);
        mapMoveEvent.mapMoveEvent(latlng);
    }

    /**
     * Вызов метода mapClickEvent у каждого слушателя при нажатии мыши
     */
    @Override
    public void onMapClick() {
        Object document = this.execScript("document");
        if (document == null) {
            throw new NullPointerException("null cannot be cast to non-null type netscape.javascript.JSObject");
        } else {
            JSObject win = (JSObject)document;
            win.setMember("java", this);
            execScript("myMap.on('click', function(e){ document.java.mapClick(e.latlng.lat, e.latlng.lng);});");
        }
    }

    /**
     * Вызов метода mapClickEvent у каждого слушателя для определенного LatLong
     * @param lat широта
     * @param lng долгота
     */
    public void mapClick(double lat, double lng) {
     //   LatLong latlng = new LatLong(lat, lng);
        System.out.println(lat + " " + lng);
     //   mapClickEvent.mapClickEvent(latlng);
    }
}
