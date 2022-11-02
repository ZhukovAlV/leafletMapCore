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
     * Координаты маркера
     */
    private boolean textMarker;

    /**
     * Текст маркера
     */
    private String text  = "";

    /**
     * Конструктор текстового маркера
     *
     * @param latLong координата
     * @param textMarker true, если текстовый маркер
     * @param text текст для текстового маркера
     * @param title заголовок для текстового маркера
     */
    public Marker(LatLong latLong, boolean textMarker, String text, String title) {
        this.latLong = latLong;
        this.textMarker = textMarker;
        this.text = text;
        this.title = title;
    }

    public Marker(LatLong latLong, Icon icon, String title) {
        this.latLong = latLong;
        this.icon = icon;
        this.title = title;
    }

    public Marker(LatLong latLong, Icon icon) {
        this.latLong = latLong;
        this.icon = icon;
    }

    @Override
    public String toString() {
        String iconUrl = "";
        if (icon != null) iconUrl = "icon: " +  icon.getId() + ", ";

        return String.join("",latLong.toString(),
                ", {", iconUrl,
                        "textMarker: ", textMarker + ", ",
                        "text: '", text, "', ",
                        "title: '", title, "'}");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.marker.toString();
    }
}