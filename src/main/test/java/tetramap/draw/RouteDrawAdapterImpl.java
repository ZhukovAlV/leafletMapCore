package tetramap.draw;

import javafx.scene.control.Alert;
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

    private final String iconStartPath = "src/main/resources/icon/route/start_path.png";
    private final String iconEndPath = "src/main/resources/icon/route/end_path.png";

    /**
     * View карты, на которой рисуется маршрут
     */
    private final MapView mapView;

    /**
     * Статус подключения
     */
    private boolean isInvoke;

    /**
     * Polyline на основе которого рисуется маршрут
     */
    private final Polyline latLongPolyline;

    /**
     * PolylineDecorator, которые добавляет стрелки и маркеры начала и конца маршрута к Polyline
     */
    private final PolylineDecorator polylineDecorator;

    public RouteDrawAdapterImpl(MapView mapView) {
        this.mapView = mapView;

        latLongPolyline = new Polyline(new ArrayList<>());
        polylineDecorator = new PolylineDecorator(latLongPolyline, iconStartPath, iconEndPath);
    }

    @Override
    public void onInvoke() {

        if (mapView.getRouteManager().isDataAvailable()) {
            mapView.getLayerGroup().addLayer(polylineDecorator);
            mapView.addLeftMouseClickListener(this);

            isInvoke = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информационное окно");
            alert.setHeaderText("Файл с картой не загружен");
            alert.setContentText("Файл с картой для построения маршрута еще не загружен. Попробуйте позднее");
            alert.showAndWait();
        }

    }

    @Override
    public void onRevoke() {
        ((LatLongArray)latLongPolyline.getLatLongs()).clear();
        ((LatLongArray)polylineDecorator.getRoutePolyline().getLatLongs()).clear();

        if (mapView.getLayerGroup().hasLayer(polylineDecorator)) mapView.getLayerGroup().removeLayer(polylineDecorator);

        removeListeners();

        isInvoke = false;
    }

    @Override
    public boolean isInvoked() {
        return isInvoke;
    }

    @Override
    public void removeListeners() {
        mapView.removeLeftMouseClickListener(this);
    }

    @Override
    public void leftMouseClicked(LatLong latLong) {

        // Добавляем новую координату
        LatLongArray latLongArray = (LatLongArray)latLongPolyline.getLatLongs();
        latLongArray.add(latLong);

        // Удаляем старый маршрут
        mapView.getLayerGroup().removeLayer(polylineDecorator);

        // Задаем новый маршрут (исключаем размер массива 1, там тогда построенный маршрут не имеет координат, т.е. 0 равен)
        if (latLongArray.size() == 1)
            polylineDecorator.setRoutePolyline(new Polyline(latLongArray));
        else
            polylineDecorator.setRoutePolyline(new Polyline(mapView.getRouteManager().getRouteFor((LatLongArray) latLongPolyline.getLatLongs())));

        // Ообновляем polylineDecorator
        mapView.getLayerGroup().addLayer(polylineDecorator);
    }
}