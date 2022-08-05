package ru.oniip.tetraleafletmap.adapters;

import ru.oniip.tetraleafletmap.draw.Circle;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.event.MapEvent;
import ru.oniip.tetraleafletmap.event.MapEvents;
import ru.oniip.tetraleafletmap.gui.LeafletMapView;
import ru.oniip.tetraleafletmap.option.CircleOptions;
import ru.oniip.tetraleafletmap.util.LatLongUtils;

import java.util.function.Consumer;

public class CircleDrawAdapter implements Consumer<MapEvent> {

    // Карта
    private final LeafletMapView mapView;

    // Настройки круга
    private Circle circle;
    private LatLng centerCircle;
    private final CircleOptions circleOptions = new CircleOptions();
    private final CircleOptions highlightCircleOptions = new CircleOptions();

    public CircleDrawAdapter(LeafletMapView mapView) {
        this.mapView = mapView;
    }

    {
        circleOptions.setColor("#3388ff");
        circleOptions.setRadius(3.0);
        circleOptions.setFill(true);
        circleOptions.setFillOpacity(1.0);
        // circleMarkerOptions.setBubblingMouseEvents(false);

        highlightCircleOptions.setColor("#3388ff");
        highlightCircleOptions.setRadius(8.0);
        highlightCircleOptions.setWeight(2.0);
        highlightCircleOptions.setFill(false);
        //  highlightCircleMarkerOptions.setBubblingMouseEvents(false);
    }

    private final Consumer<MapEvent> mapMouseMoveHandler = e -> {
        circle.setRadius(LatLongUtils.sphericalDistance(centerCircle, e.getLatlng()));
    };

    @Override
    public void accept(MapEvent mapEvent) {

        if (centerCircle != null) {
            mapView.getMap().off(MapEvents.MOUSE_MOVE, mapMouseMoveHandler);
        } else {
            centerCircle = mapEvent.getLatlng();

            // Добавление круга
            circle = mapView.getLeaflet().circle(centerCircle, circleOptions);
            circle.addTo(mapView.getMap());

            // Добавляем слой в группу слоев
            mapView.getLeaflet().layerGroup().addLayer(circle);

            // Рисование радиуса
            mapView.getMap().on(MapEvents.MOUSE_MOVE, mapMouseMoveHandler);
        }
    }

    @Override
    public Consumer<MapEvent> andThen(Consumer<? super MapEvent> after) {
        return Consumer.super.andThen(after);
    }
}
