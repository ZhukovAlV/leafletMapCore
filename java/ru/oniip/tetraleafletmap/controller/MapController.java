package ru.oniip.tetraleafletmap.controller;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.oniip.tetraleafletmap.draw.Icon;
import ru.oniip.tetraleafletmap.draw.Marker;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.option.MapOptions;
import ru.oniip.tetraleafletmap.entity.Point;
import ru.oniip.tetraleafletmap.event.MapEvents;
import ru.oniip.tetraleafletmap.gui.Leaflet;
import ru.oniip.tetraleafletmap.gui.MapPaneImpl;
import ru.oniip.tetraleafletmap.layer.TileLayer;
import ru.oniip.tetraleafletmap.option.IconOptions;
import ru.oniip.tetraleafletmap.option.MarkerOptions;
import ru.oniip.tetraleafletmap.option.PopupOptions;
import ru.oniip.tetraleafletmap.option.TileLayerOptions;

import java.util.concurrent.CompletableFuture;

public class MapController {

    @FXML
    private MapPaneImpl mapPaneImpl;

    @FXML
    private Label locationLabel;

    @FXML
    private void initialize() {
        final MapOptions mapOptions = new MapOptions();
        mapOptions.setCenter(new LatLng(55.0307f, 73.2695f));
        mapOptions.setZoom(12f);

        final CompletableFuture<Worker.State> initFuture = mapPaneImpl.getMapView().initMap(mapOptions);
        initFuture.thenAccept(state -> {
            if (state == Worker.State.SUCCEEDED) {
                mapPaneImpl.getMapView().getMap().on(MapEvents.CLICK, e -> System.out.println("map click!"));

                final Leaflet leaflet = mapPaneImpl.getMapView().getLeaflet();

                {
                    final TileLayerOptions tileLayerOptions = new TileLayerOptions();
                    tileLayerOptions.setMinZoom(1f);
                    tileLayerOptions.setMaxZoom(20f);
                    tileLayerOptions.setMaxNativeZoom(19f);
                    tileLayerOptions.setAttribution("&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors");
                    final TileLayer tileLayer = leaflet.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
                            tileLayerOptions);
                    tileLayer.addTo(mapPaneImpl.getMapView().getMap());
                }
                {
                    final IconOptions iconOptions = new IconOptions();
                    iconOptions.setIconUrl(getClass().getResource("../markerIcon/subscriber/marker_green.png").toString());
                    iconOptions.setIconAnchor(new Point(20, 20));
                    iconOptions.setIconSize(new Point(30, 30));
                    final Icon icon = leaflet.icon(iconOptions);

                    final MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.setTitle("Это маркер!");
                    final Marker marker = leaflet.marker(new LatLng(55.0387f, 73.2895f), markerOptions);
                    marker.addTo(mapPaneImpl.getMapView().getMap());
                    marker.setIcon(icon);
                    final PopupOptions popupOptions = new PopupOptions();
                    marker.bindPopup("Это всплывающее окно!", popupOptions);
                }

/*                {
                    List<LatLng> listLatLong = new ArrayList<>();
                    listLatLong.add(new LatLng(55.0307f, 73.2695f));
                    listLatLong.add(new LatLng(55.0307f, 73.2895f));
                    listLatLong.add(new LatLng(55.0507f, 73.2895f));
                    final Polygon polygon = leaflet.polygon(listLatLong);
                    polygon.addTo(mapPaneImpl.getMapView().getMap());

                }*/

/*                {
                    mapPaneImpl.getMapView().getMap().on(MapEvents.DRAW_POLYGON.getName(), e -> {
                        System.out.println("POLYGON DRAW!");
                    });
                }*/

                {
/*                    final CircleOptions circleOptions = new CircleOptions();
                    circleOptions.setRadius(40);
                    final Circle circle = leaflet.circle(new LatLng(55.0307f, 73.2695f), circleOptions);
                    circle.addTo(mapPaneImpl.getMapView().getMap());*/
/*                    circleMarker.on(CircleMarkerEvents.CLICK, e -> System.out.println("circleMarker click!"));
                    circleMarker.on(CircleMarkerEvents.DOUBLE_CLICK, e -> System.out.println("circleMarker dblclick!"));
                    circleMarker.on(CircleMarkerEvents.MOUSE_DOWN, e -> System.out.println("circleMarker mousedown!"));
                    circleMarker.on(CircleMarkerEvents.MOUSE_OVER, e -> System.out.println("circleMarker mouseover!"));
                    circleMarker.on(CircleMarkerEvents.MOUSE_OUT, e -> System.out.println("circleMarker mouseout!"));
                    circleMarker.on(CircleMarkerEvents.CONTEXT_MENU, e -> System.out.println("circleMarker contextmenu!"));*/
                }
/*
                polygon = L.polygon(polygonLatLngs);
                polygon.addTo(leafletMapView.getMap());*/

                mapPaneImpl.getMapView().getMap().on(MapEvents.MOUSE_MOVE, e ->
                        Platform.runLater(() ->
                                locationLabel.setText(String.format("%.6f, %.6f", e.getLatlng().getLat(), e.getLatlng().getLng()))));
            }
        });
    }
}
