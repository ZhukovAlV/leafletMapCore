package tetramap.entity.vectors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Contains options shared between vector overlays (Polygon, Polyline, Circle).
 */
@Data
@NoArgsConstructor
public class PathOptions implements Serializable {

	@Serial
	private static final long serialVersionUID = -7714457256248051646L;

	private boolean stroke = true;
	private String color = "#3388ff";
	private double weight = 3;
	private double opacity = 1.0;
	private String lineCap = "round";
	private String lineJoin = "round";
	private String dashArray;
	private String dashOffset;
	private boolean fill = true;
	private String fillColor = "#3388ff";
	private double fillOpacity = 0.2;
	private String fillRule = "evenodd";
	private String className;

	public PathOptions(String color) {
		this.color = color;
		this.fillColor = color;
	}

	public PathOptions(String color, double weight) {
		this.color = color;
		this.fillColor = color;
		this.weight = weight;
	}
}
