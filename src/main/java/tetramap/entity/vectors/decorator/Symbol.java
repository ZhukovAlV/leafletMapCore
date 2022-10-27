package tetramap.entity.vectors.decorator;

import tetramap.entity.vectors.PathOptions;

public class Symbol {

    /**
     * Размер символа. По умолчанию
     */
    private int pixelSize = 10;

    /**
     * Настройки стиля линии
     */
    PathOptions pathOptions = new PathOptions();

    /**
     * Тип символа. По умолчанию стрела.
     */
    TypeSymbol typeSymbol = TypeSymbol.arrowHead;

    /**
     * Типы символов для построения маршрута
     */
    public enum TypeSymbol {
        dash,
        arrowHead // Стрела
    }
}
