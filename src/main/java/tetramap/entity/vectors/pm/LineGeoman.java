package tetramap.entity.vectors.pm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.layer.LayerGeoman;
import tetramap.leaflet.LeafletObject;
import tetramap.type.TypeInstantiatesMap;

/**
 * Линия из библиотеки leaflet-geoman
 */
@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineGeoman extends LeafletObject implements LayerGeoman {

    /**
     * Включение привязки к вершинам других слоев для точного рисования
     */
    private boolean snappable = true;

    /**
     * Расстояние до другой вершины, на которой должна произойти привязка
     */
    private int snapDistance = 20;

    /**
     * Показать полезные всплывающие подсказки
     */
    private boolean tooltips = false;

    @Override
    public String toString() {
        return "{ snappable: " + snappable +
                ", snapDistance: " + snapDistance +
                ", tooltips: " + tooltips +
                "}";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.LINE_PM.getName();
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        mapView.execScript(String.join("","var ", this.getId(), " = "
                + mapView.getMap().getId() +".pm.enableDraw('", this.getTypeInstantiatesMap(), "',", this.toString(), ");"));
    }

    @Override
    public void updateTo() {

        log.info("Удаление с карты слоя geoman layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");

        log.info("Обновляем значение geoman layer: {}", this.getId());
        getMapView().execScript(String.join("",this.getId(), " = "
                + getMapView().getMap().getId() +".pm.enableDraw('", this.getTypeInstantiatesMap(), "',", this.toString(), ");"));
    }

    @Override
    public void disable() {
        log.info("Отключение режима редактирования geoman layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".disable();");
    }

    @Override
    public boolean enabled() {
        boolean isEnabled = (boolean)getMapView().execScript(this.getId() + ".enabled();");
        log.info("Режим редактирования layer: {}", this.getId() + " " + isEnabled);
        return isEnabled;
    }

    @Override
    public boolean hasSelfIntersection() {
        boolean isEnabled = (boolean)getMapView().execScript(this.getId() + ".hasSelfIntersection();");
        log.info("Самопересечение layer: {}", this.getId() + " " + isEnabled);
        return isEnabled;
    }

    @Override
    public void remove() {
        log.info("Удаление geoman layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".pm.remove();");
    }
}
