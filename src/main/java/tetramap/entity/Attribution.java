package tetramap.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

/**
 * Устанавливает префикс (название слоя карты) в углу карты
 */
@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attribution extends LeafletObject {

    @Serial
    private static final long serialVersionUID = 9098765502642340035L;

    private String prefix = "Leaflet";

    /**
     * Добавление префикса к карте
     */
    public void setPrefix(MapView mapView) {
        log.info("Установка префикса объекту Attribution (название слоя карты в углу карты), id: {}", this.getId());
        mapView.execScript(String.join("", this.getId(), ".setPrefix('", this.getPrefix() , "');"));
    }

    public void createTo(MapView mapView) {
        log.info("Создание Attribution (название слоя карты в углу карты), id: {}", getId());
        mapView.execScript(String.join("","var ", this.getId(), " = ", mapView.getMap().getId(), ".", this.getTypeInstantiatesMap(), ";"));
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.attributionControl.toString();
    }
}

