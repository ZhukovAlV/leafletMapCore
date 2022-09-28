package tetramap.entity.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.BaseMaps;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletControl;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
@AllArgsConstructor
public class LayersControl extends LeafletControl {

    @Serial
    private static final long serialVersionUID = -7779809624116362068L;

    private final BaseMaps tileLayerList;

    /**
     * Создание слоя во View карты
     * @param mapView View карты
     */
    @Override
    public void createTo(MapView mapView) {
        log.info("Создание LayersControl: {}", ", id: " + this.getId());
        mapView.execScript(String.join("","var ", this.getId(), " = L.",
                this.getTypeInstantiatesMap(), ".layers(", tileLayerList.getId(), ", {});"));
    }

    /**
     * Добавление control во View карты
     *
     * @param mapView View карты
     */
    @Override
    public void addTo(MapView mapView) {
        log.info("Добавление LayersControl: {}", "id: " + this.getId());
        if (tileLayerList.getTileLayers().size() > 1) {
            mapView.execScript(this.getId() + ".addTo(" + mapView.getMap().getId() + ");");
        } else log.warn("Список тайловых слоев пуст в LayersControl: {}", "id: " + this.getId());
    }
}
