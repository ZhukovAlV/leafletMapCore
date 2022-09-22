package tetramap.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.extern.log4j.Log4j2;
import tetramap.config.MapConfig;
import tetramap.config.ScaleControlConfig;
import tetramap.config.ZoomControlConfig;
import tetramap.entity.LatLong;
import tetramap.layer.Layer;
import tetramap.type.MapLayerType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log4j2
public class LeafletMap {

    private static final long serialVersionUID = 3789693345308589828L;

    // Контейнер для html карты
    private final WebView webView = new WebView();
    private final WebEngine webEngine;

    public LeafletMap() {
        webEngine = webView.getEngine();
    }

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
        Iterator<MapLayerType> iterator;
        int index;

        // Настройки Layers
        List<MapLayerType> configLayers = mapConfig.getLayers();
        iterator = configLayers.iterator();
        index = 0;
        while(iterator.hasNext()) {
            MapLayerType layer = iterator.next();
            stringBuilder = (new StringBuilder()).append("var layer").append(++index).append(" = ");
            execScript(stringBuilder.append(layer.getJavaScriptCode()).append(';').toString());
        }
        configLayers = mapConfig.getLayers();

        Iterable<MapLayerType> iterable = configLayers;
        Collection<String> destinationList = new ArrayList<>();
        index = 0;
        iterator = iterable.iterator();
        while(iterator.hasNext()) {
            MapLayerType layer = iterator.next();
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
    }

    public Object execScript(String script) {
        return webEngine.executeScript(script);
    }

    public WebView getWebView() {
        return webView;
    }

    public void addLayer(Layer layer) {
      //  logger.debug("add layer: {}", layer);
       // this.mapLayer.addLayer(layer);
       // executeJs("addLayer", layer);
        System.out.println(layer);

        if (layer.getLeafletType().equals("MarkerLeaflet"))
            execScript("L.marker(" + layer + ").addTo(map);");
        else execScript("var " + layer.getId() + " = L.icon(" + layer + ");");
      //  mapView.execScript("var myIcon = L.icon({iconUrl: '" + icon.getIconUrl() + "', iconSize: [24, 24], iconAnchor: [12, 12], });");
      //  mapView.execScript("L.marker([" + latLong.getLatitude() + "," + latLong.getLongitude() + "], {icon: myIcon}).addTo(map);");*/
    }

}
