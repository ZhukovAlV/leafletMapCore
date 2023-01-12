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

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Log4j2
public class PolylineDecorator extends Layer {

    private static final long serialVersionUID = 4997212552783738174L;

    /**
     * Фигура для которой накладывается декоратор
     */
    private Polyline polyline;

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
     * Иконка для маркеров
     */
    private Icon icon;

    public PolylineDecorator(Polyline polyline) {
        this.polyline = polyline;
        offset = 50;
        endOffset = 50;
        repeat = 100;
        symbol = new Symbol();
    }

    @Override
    public String toString() {
        return polyline.getId() + "," + "{patterns: [{" +
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

        symbol.addTo(mapView);

        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),
                "(", this.toString(), ").addTo(", mapView.getMap().getId() + ");"));

        try {
            icon = new Icon(getClass().getResource("/markerIcon/subscriber/marker_green.png").getPath());
            icon.addTo(mapView);
        } catch (NullPointerException e){
            log.error("Иконка маркера маршрута не была загружена");
        }

        LatLongArray latLongArray = (LatLongArray)polyline.getLatLongs();
        
        LatLong latLongStart = latLongArray.get(0);
        startMarker = new Marker(latLongStart, icon, latLongStart.toString());
        startMarker.addTo(mapView);

        LatLong latLongEnd = latLongArray.get(latLongArray.size() - 1);
        endMarker = new Marker(latLongEnd, icon, latLongEnd.toString());
        endMarker.addTo(mapView);
    }

    @Override
    public void updateTo() {
        remove();

        log.info("Обновляем значение layer: {}", this.getId());
        getMapView().execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),
                "(", this.toString(), ").addTo(", getMapView().getMap().getId() + ");"));

        LatLong latLongStart = ((LatLongArray)polyline.getLatLongs()).get(0);
        startMarker = new Marker(latLongStart, icon, latLongStart.toString());
        startMarker.addTo(getMapView());

        LatLong latLongEnd = ((LatLongArray)polyline.getLatLongs()).get(0);
        endMarker = new Marker(latLongEnd, icon, latLongEnd.toString());
        endMarker.addTo(getMapView());
    }

    @Override
    public void remove() {
        log.info("Удаление с карты слоя layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");

        startMarker.remove();
        endMarker.remove();
    }
}
