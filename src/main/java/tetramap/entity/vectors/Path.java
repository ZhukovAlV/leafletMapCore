package tetramap.entity.vectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tetramap.layer.InteractiveLayer;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Path extends InteractiveLayer {

    @Serial
    private static final long serialVersionUID = 8625609400908426994L;

    private String color = "#3388ff";
    private String fillColor = "#3388ff";
    private double weight = 3;
}
