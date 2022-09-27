package tetramap.entity.control;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

/**
 * Устанавливает префикс (название слоя карты)
 */
@Getter
@Setter
@Log4j2
public class LAttributionControl extends LeafletControl {

    @Serial
    private static final long serialVersionUID = 9098765502642340035L;

    private String prefix = "";

    /**
     * Добавление префикса к карте
     */
    public void setPrefix(MapView mapView) {
        log.info("Установка префикса объекту LAttributionControl: {}", "id: " + this.getId());
        mapView.execScript(this.getId() + ".setPrefix('" + this.getPrefix() + "');");
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.attributionControl.toString();
    }
}

