package tetramap.entity.control;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletControl;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

/**
 * Устанавливает префикс (название слоя карты)
 */
@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributionControl extends LeafletControl {

    @Serial
    private static final long serialVersionUID = 9098765502642340035L;

    private String prefix = "Leaflet";

    /**
     * Добавление префикса к карте
     */
    public void setPrefix(MapView mapView) {
        log.info("Установка префикса объекту AttributionControl: {}", "id: " + this.getId());
        mapView.execScript(String.join("", this.getId(), ".setPrefix('", this.getPrefix() , "');"));
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.attributionControl.toString();
    }
}

