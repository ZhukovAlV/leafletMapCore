package tetramap.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import tetramap.entity.Icon;

import java.io.Serial;

@Data
@AllArgsConstructor
public class IconLeaflet implements Icon {

    /**
     * Ссылка на иконку
     */
    String iconUrl;
}
