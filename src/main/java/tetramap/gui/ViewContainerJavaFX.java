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
import tetramap.entity.LBaseMaps;
import tetramap.entity.LMap;
import tetramap.entity.LTileLayer;
import tetramap.entity.LatLong;
import tetramap.layer.Layer;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log4j2
public class ViewContainerJavaFX implements ViewContainer {

    private static final long serialVersionUID = 4789694456308589829L;

    // Контейнер для html
    private final WebView webView = new WebView();
    private final WebEngine webEngine;

    // Карта
    private final LMap lMap = new LMap();

    public ViewContainerJavaFX() {
        webEngine = webView.getEngine();
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

        // Установка центра карты, зума, отображения 1 слоя
        LatLong latLng = mapConfig.getInitialCenter();
        stringBuilder = (new StringBuilder()).append("var map = L.map('map', {center: new L.LatLng(");
        stringBuilder.append(latLng.getLatitude()).append(", ")
                .append(latLng.getLongitude()).append("),zoom: 14,zoomControl: false,layers: [" + tileLayerList.get(0).getId() + "]});")
                .append("var attribution = map.attributionControl;attribution.setPrefix('');");
        execScript(stringBuilder.toString());
        if (mapConfig.getLayers().size() > 1) {
            execScript("var overlayMaps = {};L.control.layers(" + baseMaps.getId() + ", overlayMaps).addTo(map);");
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

    @Override
    public void execScript(String script) {
        webEngine.executeScript(script);
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    public void addLayer(Layer layer) {
        log.info("add layer: {}", layer);
        execScript(layer.getId() + ".addTo(map);");
    }
}
