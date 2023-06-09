package tetramap.entity.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.BaseMaps;
import tetramap.gui.MapView;
import tetramap.leaflet.LeafletControl;
import tetramap.type.TypeInstantiatesMap;

@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
@AllArgsConstructor
public class LayersControl extends LeafletControl {

    private static final long serialVersionUID = -7779809624116362068L;

    private final BaseMaps tileLayerList;

    @Override
    public String toString() {
        return tileLayerList.getId() + ", {}";
    }

    @Override
    public void createTo(MapView mapView) {
        if (tileLayerList.getTileLayers().size() > 1) {
            setMapView(mapView);

            log.info("Создание LayersControl, id: {}", this.getId());
            mapView.execScript(String.join("",this.getId(), " = L.",
                    TypeInstantiatesMap.CONTROL.getName(), ".", this.getTypeInstantiatesMap(), "(",this.toString(), ");"));

        } else log.warn("Список тайловых слоев пуст в LayersControl: {}", this.getId());
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.LAYERS.getName();
    }
}
