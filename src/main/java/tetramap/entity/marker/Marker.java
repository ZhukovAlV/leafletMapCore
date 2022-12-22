package tetramap.entity.marker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.types.Icon;
import tetramap.entity.types.LatLong;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Log4j2
public class Marker extends Layer {

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
     * Конструктор текстового маркера
     *
     * @param latLong координата
     * @param title заголовок для текстового маркера
     */
    public Marker(LatLong latLong, String title) {
        this.latLong = latLong;
        this.title = title;
    }

    public Marker(LatLong latLong) {
        this.latLong = latLong;
    }

    @Override
    public String toString() {
        // Прозрачный фон если иконка не выставлена
        String iconUrl = "opacity: 0, ";
        if (icon != null) iconUrl = "icon: " +  icon.getId() + ", ";

        return String.join("",latLong.toString(),
                ", {", iconUrl, "title: '", title, "'}");
    }

    /**
     * Текст для маркера
     */
    public void bindTooltip(String text){
        log.info("Добавляется Popup к layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".bindTooltip('" + text + "', {permanent: true, className: 'my-label', offset: [0, 0] });");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.MARKER.getName();
    }
}