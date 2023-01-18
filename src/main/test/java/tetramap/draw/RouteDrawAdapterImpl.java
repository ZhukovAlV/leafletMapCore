package tetramap.draw;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.RouteDrawAdapter;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.decorator.PolylineDecorator;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.gui.MapView;

import java.util.ArrayList;

@Getter
@Log4j2
public class RouteDrawAdapterImpl implements RouteDrawAdapter {

    /**
     * View карты, на которой рисуется маршрут
     */
    private final MapView mapView;

    /**
     * Polyline на основе которого рисуется маршрут
     */
    private final Polyline latLongPolyline;

    /**
     * PolylineDecorator, которые добавляет стрелки и маркеры начала и конца маршрута к Polyline
     */
    private final PolylineDecorator polylineDecorator;

    public RouteDrawAdapterImpl(MapView mapView, String iconStartPath, String iconEndPath) {
        this.mapView = mapView;

        latLongPolyline = new Polyline(new ArrayList<>());
        polylineDecorator = new PolylineDecorator(latLongPolyline, iconStartPath, iconEndPath);
    }

    @Override
    public void onInvoke() {
        mapView.getLayerGroup().addLayer(polylineDecorator);

        mapView.addLeftMouseClickListener(this);
    }

    @Override
    public void onRevoke() {
        ((LatLongArray)latLongPolyline.getLatLongs()).clear();
        ((LatLongArray)polylineDecorator.getRoutePolyline().getLatLongs()).clear();

        mapView.getLayerGroup().removeLayer(polylineDecorator);

        mapView.removeLeftMouseClickListener(this);
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {

        // Добавляем новую координату
        LatLongArray latLongArray = (LatLongArray)latLongPolyline.getLatLongs();
        latLongArray.add(latLong);

        // Удаляем старый маршрут
        polylineDecorator.remove();

        // Задаем новый маршрут (исключаем размер массива 1, там тогда построенный маршрут не имеет координат, т.е. 0 равен)
        if (latLongArray.size() == 1)
            polylineDecorator.setRoutePolyline(new Polyline(latLongArray));
        else
            polylineDecorator.setRoutePolyline(new Polyline(mapView.getRouteManager().getRouteFor((LatLongArray) latLongPolyline.getLatLongs())));

        // Ообновляем polylineDecorator
        polylineDecorator.addTo(mapView);
    }
}
