package tetramap.entity.vectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.layer.InteractiveLayer;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public abstract class Path extends InteractiveLayer implements HasStyle {

    private static final long serialVersionUID = 8625609400908426994L;

    /**
     * Класс с параметрами (стилями и тд.)
     */
    private PathOptions pathOptions;

    public Path() {
        pathOptions = new PathOptions();
    }

    public boolean isStroke() {
        return pathOptions.isStroke();
    }

    /**
     * Следует ли рисовать обводку вдоль контура. Установите для него значение false, чтобы отключить границы для
     *
     * @param stroke установите для него значение false, чтобы отключить границы для полигонов или кругов
     */
    public void setStroke(boolean stroke) {
        this.pathOptions.setStroke(stroke);
    }

    public String getColor() {
        return pathOptions.getColor();
    }

    /**
     * Цвет штриха
     *
     * @param color цвет штриха
     */
    public void setColor(String color) {
        this.pathOptions.setColor(color);
    }

    public double getWeight() {
        return pathOptions.getWeight();
    }

    /**
     * Ширина штриха в пикселях
     *
     * @param weight ширина в пикселях
     */
    public void setWeight(double weight) {
        this.pathOptions.setWeight(weight);
    }

    public double getOpacity() {
        return pathOptions.getOpacity();
    }

    /**
     * Непрозрачность штриха
     *
     * @param opacity непрозрачность штриха
     */
    public void setOpacity(double opacity) {
        this.pathOptions.setOpacity(opacity);
    }

    public String getLineCap() {
        return pathOptions.getLineCap();
    }

    /**
     * Строка, определяющая форму, которая будет использоваться в конце штриха
     *
     * @param lineCap форма, которая будет использоваться в конце штриха
     */
    public void setLineCap(String lineCap) {
        this.pathOptions.setLineCap(lineCap);
    }

    public String getLineJoin() {
        return pathOptions.getLineJoin();
    }

    /**
     * Строка, определяющая форму, которая будет использоваться в углах штриха
     *
     * @param lineJoin форма, которая будет использоваться в углах штриха
     */
    public void setLineJoin(String lineJoin) {
        this.pathOptions.setLineJoin(lineJoin);
    }

    public String getDashArray() {
        return pathOptions.getDashArray();
    }

    /**
     * Строка, определяющая шаблон штриховой черточки
     *
     * @param dashArray определяет шаблон штриховой черточки
     */
    public void setDashArray(String dashArray) {
        this.pathOptions.setDashArray(dashArray);
    }

    public String getDashOffset() {
        return pathOptions.getDashOffset();
    }

    /**
     * Строка, определяющая расстояние в шаблоне штриховки для начала штриховки
     *
     * @param dashOffset определяет расстояние в шаблоне штриховки для начала
     */
    public void setDashOffset(String dashOffset) {
        this.pathOptions.setDashOffset(dashOffset);
    }

    public boolean isFill() {
        return pathOptions.isFill();
    }

    /**
     * Следует ли заливать контур цветом. Установите для него значение false, чтобы отключить заполнение
     *
     * @param fill установите для него значение false, чтобы отключить заполнение полигонов или кругов
     */
    public void setFill(boolean fill) {
        this.pathOptions.setFill(fill);
    }

    public String getFillColor() {
        return pathOptions.getFillColor();
    }

    /**
     * Цвет заливки. По умолчанию используется значение параметра цвет
     *
     * @param fillColor цвет заливки контура
     */
    public void setFillColor(String fillColor) {
        this.pathOptions.setFillColor(fillColor);
    }

    public double getFillOpacity() {
        return pathOptions.getFillOpacity();
    }

    /**
     * Непрозрачность
     *
     * @param fillOpacity непрозрачность заливки (от 0 до 1)
     */
    public void setFillOpacity(double fillOpacity) {
        this.pathOptions.setFillOpacity(fillOpacity);
    }

    public String getFillRule() {
        return pathOptions.getFillRule();
    }

    /**
     * Строка, которая определяет, как определяется внутренняя часть фигуры
     *
     * @param fillRule строка, которая определяет, как выглядит внутренняя часть фигуры
     */
    public void setFillRule(String fillRule) {
        this.pathOptions.setFillRule(fillRule);
    }

    @Override
    public PathOptions getStyle() {
        return pathOptions;
    }

    @Override
    public void setStyle(PathOptions pathOptions) {
        this.pathOptions = pathOptions;
    }
}
