package tetramap.entity.vectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Содержит параметры, общие для фигур (Polygon, Polyline, Circle).
 */
@AllArgsConstructor
@Getter
@Setter
public class PathOptions implements Serializable {

	private static final long serialVersionUID = -7714457256248051646L;

	/**
	 * Нужно ли рисовать обводку вдоль пути
	 */
	private boolean stroke;

	/**
	 * Цвет по умолчанию синий
	 */
	private String color;

	/**
	 * Толщина по умолчанию 3
	 */
	private double weight;

	/**
	 * Непрозрачность по умолчанию 100%
	 */
	private double opacity;

	/**
	 * Строка, определяющая форму, которая будет использоваться в конце штриха
	 */
	private String lineCap;

	/**
	 * Строка, определяющая форму, которая будет использоваться в углах штриха
	 */
	private String lineJoin;

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
	private String fillColor;

	/**
	 * Заполнить непрозрачность
	 */
	private double fillOpacity;

	/**
	 * Строка, определяющая, как определяется внутренняя часть фигуры
	 */
	private String fillRule = "evenodd";

	public PathOptions() {
		this.stroke = true;
		this.color = "#3388ff";
		this.weight = 3;
		this.opacity = 1.0;
		this.lineCap = "round";
		this.lineJoin = "round";
/*		this.dashArray = ;
		this.dashOffset = ;
		this.fill = ;*/
		this.fillColor = "#3388ff";
		this.fillOpacity = 0.2;
	}

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
