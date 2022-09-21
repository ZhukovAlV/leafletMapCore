package tetramap.gui;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
import tetramap.js.ExecutableFunctions;
import tetramap.js.Identifiable;
import tetramap.js.LeafletObject;
import tetramap.layer.Layer;
import tetramap.operations.LeafletOperation;
import tetramap.type.MapLayerType;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log4j2
public class LeafletMap extends LeafletObject {

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

    @Override
    public void executeJs(Identifiable target, String functionName, Serializable... arguments) {
        // logger.info("Execute leaflet function: {}", functionName);
        LeafletOperation leafletOperation = new LeafletOperation(target, functionName, arguments);
     //   getElement().callJsFunction("callLeafletFunction", JsonSerializer.toJson(leafletOperation));
        // TODO нужно скрипт размещения на карту доделать
        System.out.println(leafletOperation.getArguments());
/*        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String leafletOperationAsString = objectMapper.writeValueAsString(leafletOperation);
            System.out.println(leafletOperationAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
/*
    @Override
    public <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName, Class<T> resultType, Serializable... arguments) {
*//*        if (ready) {
            logger.info("Call leaflet function: {}", functionName);
            LeafletOperation leafletOperation = new LeafletOperation(target, functionName, arguments);
            PendingJavaScriptResult javascriptResult = getElement().callJsFunction("callLeafletFunction", JsonSerializer.toJson(leafletOperation));

            CompletableFuture<T> completableFuture = new CompletableFuture<>();
            javascriptResult.then(value -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    T result;
                    // Detect object type for value to be handled correctly (ex: getZoom)
                    JsonType type = value.getType();
                    if ( type.equals(JsonType.OBJECT)) {
                        result = objectMapper.readValue(value.toString(), resultType);
                    }
                    else {
                        result = objectMapper.readValue(value.asString(), resultType);
                    }
                    completableFuture.complete(result);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to parse javascript result", e);
                }
            }, errorValue -> {
                JavaScriptException exception = new JavaScriptException(errorValue);
                completableFuture.completeExceptionally(exception);
            });
            return completableFuture;
        } else {
            return null;
        }*//*
        return null;
    }

    @Override
    public String getUuid() {
        return null;
    }*/

    public void addLayer(Layer layer) {
      //  logger.debug("add layer: {}", layer);
       // this.mapLayer.addLayer(layer);
        executeJs("addLayer", layer);
    }

    @Override
    public <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName, Class<T> resultType, Serializable... arguments) {
        return null;
    }
}
