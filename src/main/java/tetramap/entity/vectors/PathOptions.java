package tetramap.entity.vectors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Содержит параметры, общие для фигур (Polygon, Polyline, Circle).
 */
@Data
@NoArgsConstructor
public class PathOptions implements Serializable {

	private static final long serialVersionUID = -7714457256248051646L;

	/**
	 * Нужно ли рисовать обводку вдоль пути
	 */
	private boolean stroke = true;

	/**
	 * Цвет по умолчанию синий
	 */
	private String color = "#3388ff";

	/**
	 * Толщина по умолчанию 3
	 */
	private double weight = 3;

	/**
	 * Непрозрачность по умолчанию 100%
	 */
	private double opacity = 1.0;

	/**
	 * Строка, определяющая форму, которая будет использоваться в конце штриха
	 */
	private String lineCap = "round";

	/**
	 * Строка, определяющая форму, которая будет использоваться в углах штриха
	 */
	private String lineJoin = "round";

	/**
	 * Строка, определяющая шаблон штрих-тире
	 */
	private String dashArray;

	/**
	 * Строка, определяющая расстояние до шаблона штриха, с которого начинается штрих
	 */
	private String dashOffset;

	/**
	 * Заполнять ли путь цветом. Установите значение false, чтобы отключить заливку многоугольников или кругов
	 */
	private boolean fill;

	/**
	 * Цвет заливки. По умолчанию значение параметра цвета
	 */
	private String fillColor = "#3388ff";

	/**
	 * Заполнить непрозрачность
	 */
	private double fillOpacity = 0.2;

	/**
	 * Строка, определяющая, как определяется внутренняя часть фигуры
	 */
	private String fillRule = "evenodd";

	public PathOptions(String color) {
		this.color = color;
		this.fillColor = color;
	}

	public PathOptions(String color, double weight) {
		this.color = color;
		this.fillColor = color;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return String.join("","{",
				"stroke: " + stroke,
				", color: '", color, "'",
				", weight: " + weight,
				", opacity: " + opacity,
				", lineCap: '", lineCap, "'",
				", lineJoin: '", lineJoin, "'",
				", dashArray: '", dashArray, "'",
				", dashOffset: '", dashOffset, "'",
				", fill: " + fill,
				", fillColor: '", fillColor, "'",
				", fillOpacity: " + fillOpacity,
				", fillRule: '", fillRule, "'",
				"}");
	}
}
