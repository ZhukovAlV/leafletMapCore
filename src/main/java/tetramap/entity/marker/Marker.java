package tetramap.entity.marker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.entity.types.Icon;
import tetramap.entity.types.LatLong;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Marker extends Layer {

    @Serial
    private static final long serialVersionUID = 5997712572773708479L;

    /**
     * Координаты маркера
     */
    private LatLong latLong;

    /**
     * Иконка маркера
     */
    private Icon icon;

    /**
     * Заголовок маркера
     */
    private String title  = "";

    /**
     * Конструктор без названия
     */
    public Marker(LatLong latLong, Icon icon) {
        this.latLong = latLong;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return String.join("",latLong.toString(),
                ", {icon: ", icon.getId(), ", " +
                        "title: '" + title + "'}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.marker.toString();
    }
}