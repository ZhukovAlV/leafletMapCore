package tetramap.entity.vectors.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.marker.Marker;
import tetramap.entity.types.Icon;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.Polyline;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.gui.MapView;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

import java.io.File;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Log4j2
public class PolylineDecorator extends Layer {

    private static final long serialVersionUID = 4997212552783738174L;

    /**
     * Фигура для которой накладывается декоратор
     */
    private Polyline routePolyline;

    /**
     * Смещение первого символа шаблона от начальной точки линии. По умолчанию: 0.
     */
    private int offset;

    /**
     * Минимальное смещение последнего символа шаблона от конечной точки линии. По умолчанию: 0.
     */
    private int endOffset;

    /**
     * Интервал повторения символов шаблона. Определяет расстояние между точками привязки каждого последующего символа.
     */
    private int repeat;

    /**
     * Используемый символ (стрелка или пунктир). По умолчанию стрелка.
     */
    private Symbol symbol;

    /**
     * Маркеры для обозначения начала и конца пути
     */
    private Marker startMarker;
    private Marker endMarker;

    /**
     * Иконки для маркеров
     */
    private Icon iconStart;
    private Icon iconEnd;

    /**
     * Путь к картинкам иконок для маркеров
     */
    public String iconStartPath = "src/main/resources/icon/route/start_path.png";
    public String iconEndPath = "src/main/resources/icon/route/end_path.png";

    public PolylineDecorator(Polyline routePolyline, String iconStartPath, String iconEndPath) {
        this(routePolyline);

        this.iconStartPath = iconStartPath;
        this.iconEndPath = iconEndPath;
    }

    public PolylineDecorator(Polyline routePolyline) {
        this.routePolyline = routePolyline;

        this.offset = 50;
        this.endOffset = 50;
        this.repeat = 100;
        this.symbol = new Symbol();
    }

    @Override
    public String toString() {
        return routePolyline.getId() + "," + "{patterns: [{" +
                "offset: " + offset +
                ", endOffset: " + endOffset +
                ", repeat: " + repeat +
                ", symbol: " + symbol.getId() +
                "}]}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.POLYLINE_DECORATOR.getName();
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        routePolyline.addTo(mapView);
        symbol.addTo(mapView);

        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),
                "(", this.toString(), ").addTo(", mapView.getMap().getId() + ");"));

        try {
            iconStart = new Icon((new File(iconStartPath)).getAbsolutePath());
            iconStart.addTo(mapView);

            iconEnd = new Icon((new File(iconEndPath)).getAbsolutePath());
            iconEnd.addTo(mapView);
        } catch (NullPointerException e){
            log.error("Иконка маркера маршрута не была загружена");
        }

        LatLongArray latLongArray = (LatLongArray) routePolyline.getLatLongs();
        if (!latLongArray.isEmpty()) {
            setIcons(latLongArray);
        }
    }

    @Override
    public void updateTo() {
        remove();

        routePolyline.addTo(getMapView());

        log.info("Обновляем значение layer: {}", this.getId());
        getMapView().execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),
                "(", this.toString(), ").addTo(", getMapView().getMap().getId() + ");"));

        setIcons((LatLongArray) routePolyline.getLatLongs());
    }

    @Override
    public void remove() {
        log.info("Удаление с карты слоя layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");

        if (startMarker != null) startMarker.remove();
        if (endMarker != null) endMarker.remove();

        routePolyline.remove();
    }

    public void setIcons(LatLongArray latLongArray) {
        LatLong latLongStart = latLongArray.get(0);
        startMarker = new Marker(latLongStart, iconStart, latLongStart.toString());
        startMarker.addTo(getMapView());

        LatLong latLongEnd = latLongArray.get(latLongArray.size() - 1);
        endMarker = new Marker(latLongEnd, iconEnd, latLongEnd.toString());
        endMarker.addTo(getMapView());
    }
}
