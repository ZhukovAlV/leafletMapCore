package tetramap.entity.vectors.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.vectors.Path;
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
    private Path path;

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

    public PolylineDecorator(Path path) {
        this.path = path;
        offset = 0;
        endOffset = 0;
        repeat = 100;
        symbol = new Symbol();
    }

    @Override
    public String toString() {
        return path.getId() + "," + "{patterns: [{" +
                "offset: " + offset +
                ", endOffset: " + endOffset +
                ", repeat: " + repeat +
                ", symbol: L.Symbol." + symbol.getTypeSymbol() + "(" + symbol.toString() +
                ")}]}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.POLYLINE_DECORATOR.getName();
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),
                "(", this.toString(), ").addTo(", mapView.getMap().getId() + ");"));
    }

    @Override
    public void updateTo() {
        log.info("Удаление с карты слоя layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");

        log.info("Обновляем значение layer: {}", this.getId());
        getMapView().execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(),
                "(", this.toString(), ").addTo(", getMapView().getMap().getId() + ");"));
    }
}
