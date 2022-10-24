package tetramap.entity.vectors.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolylineDecorator {

    /**
     * Смещение первого символа шаблона от начальной точки линии. Значение по умолчанию: 0.
     */
    private int offset;

    /**
     * Минимальное смещение последнего символа шаблона от конечной точки линии. Значение по умолчанию: 0.
     */
    private int endOffset;

    /**
     * Интервал повторения символов шаблона. По умолчанию 100
     */
    private int repeat = 100;

    /**
     * Используемый символ (стрелка или пунктир). По умолчанию стрелка.
     */
    private Symbol symbol = new Symbol();

    public PolylineDecorator() {
        // По умолчанию Symbol стрелка

    }
}
