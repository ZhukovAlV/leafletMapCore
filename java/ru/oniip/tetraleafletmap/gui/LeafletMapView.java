package ru.oniip.tetraleafletmap.gui;

import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import ru.oniip.tetraleafletmap.option.MapOptions;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

/**
 * Leaflet map view.
 */
public class LeafletMapView extends StackPane {

  private final WebView webView = new WebView();

  private Leaflet leaflet;
  private LeafletMap map;

  /**
   * Constructs a new LeafletMapView.
   */
  public LeafletMapView() {
    super();

    this.getChildren().add(webView);
  }

  /**
   * Gets the Leaflet method factory.
   *
   * @return Leaflet method factory.
   */
  public Leaflet getLeaflet() {
    return leaflet;
  }

  /**
   * Gets the map.
   *
   * @return Map.
   */
  public LeafletMap getMap() {
    return map;
  }

  /**
   * Initializes the map view.
   *
   * @param mapOptions      Map options.
   * @return Completable future.
   */
  public CompletableFuture<Worker.State> initMap(MapOptions mapOptions) {
    final CompletableFuture<Worker.State> stateCompletableFuture = new CompletableFuture<>();
    final URL url = getClass().getResource("../web/index.html");
    webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == Worker.State.SUCCEEDED) {
        final JSContext jsContext = new JSContext(webView.getEngine());

        leaflet = new LeafletImpl(jsContext);
        map = leaflet.map("map", mapOptions);
      }

      if ((newValue == Worker.State.SUCCEEDED) || (newValue == Worker.State.FAILED)) {
        stateCompletableFuture.complete(newValue);
      }
    });
    webView.getEngine().load(url.toExternalForm());

    return stateCompletableFuture;
  }
}
