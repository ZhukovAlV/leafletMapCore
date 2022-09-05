package tetramap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;

@Data
@AllArgsConstructor
public class IconLeaflet implements Icon {

    @Serial
    private static final long serialVersionUID = -5884170824139869060L;

    /**
     * Ссылка на иконку
     */
    String iconUrl;
}
