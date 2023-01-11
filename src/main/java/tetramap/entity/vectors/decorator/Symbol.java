package tetramap.entity.vectors.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.vectors.PathOptions;
import tetramap.gui.MapView;
import tetramap.layer.Layer;

import static tetramap.type.TypeInstantiatesMap.SYMBOL_DECORATOR;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Log4j2
public class Symbol extends Layer {

    /**
     * Размер символа. По умолчанию
     */
    private int pixelSize;

    /**
     * Настройки стиля линии
     */
    PathOptions pathOptions;

    /**
     * Тип символа. По умолчанию стрела.
     */
    TypeSymbol typeSymbol;

    public Symbol() {
        pixelSize = 10;

        pathOptions = new PathOptions();
        pathOptions.setFillOpacity(1);
        pathOptions.setFill(true);

        typeSymbol = TypeSymbol.arrowHead;
    }

    @Override
    public String toString() {
        return "{" +
                "pixelSize: " + pixelSize +
                ", pathOptions: " + pathOptions +
                '}';
    }

    @Override
    public String getTypeInstantiatesMap() {
        return SYMBOL_DECORATOR.getName();
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), ".",
                typeSymbol.name(), "(", this.toString(), ").addTo(", mapView.getMap().getId() + ");"));
    }

    @Override
    public void updateTo() {
        log.info("Удаление с карты слоя layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");

        log.info("Обновляем значение layer: {}", this.getId());
        getMapView().execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), ".",
                typeSymbol.name(), "(", this.toString(), ").addTo(", getMapView().getMap().getId() + ");"));
    }

    /**
     * Типы символов для построения маршрута
     */
    public enum TypeSymbol {
        dash,
        arrowHead // Стрела
    }
}
