package tetramap.entity.vectors.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tetramap.entity.vectors.PathOptions;

@AllArgsConstructor
@Getter
public class Symbol {

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

    /**
     * Типы символов для построения маршрута
     */
    public enum TypeSymbol {
        dash,
        arrowHead // Стрела
    }
}
