package tetramap.gui;

import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import netscape.javascript.JSObject;
import tetramap.config.MapConfig;
import tetramap.entity.LatLong;
import tetramap.event.MapClickEventListener;
import tetramap.event.MapClickEventManager;
import tetramap.event.MapMoveEventListener;
import tetramap.event.MapMoveEventManager;

import java.util.concurrent.CompletableFuture;

/**
 * Карта от Leaflet для JavaFX
 */
public class MapViewLeaflet extends StackPane implements MapView {

    // Карта
    private final LeafletMap leafletMap = new LeafletMap();

    // Менеджер на нажатие мыши
    private final MapClickEventManager mapClickEventManager;
    // Менеджер на перемещение мыши
    private final MapMoveEventManager mapMoveEventManager;

    public MapViewLeaflet() {
        getChildren().add(leafletMap.getWebView());
        mapClickEventManager = new MapClickEventManager();
        mapMoveEventManager = new MapMoveEventManager();
    }

    @Override
    public void setSize(double width, double height) {
        leafletMap.getWebView().setPrefSize(width, height);
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
    public CompletableFuture<Worker.State> displayMap(MapConfig mapConfig) {
        return leafletMap.displayMap(mapConfig);
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
}
