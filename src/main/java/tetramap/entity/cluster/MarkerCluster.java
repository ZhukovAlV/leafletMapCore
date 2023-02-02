package tetramap.entity.cluster;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.gui.MapView;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

/**
 * Класс кластера маркеров
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Log4j2
public class MarkerCluster extends Layer {

    private boolean spiderfyOnMaxZoom;
    private boolean showCoverageOnHover;
    private boolean zoomToBoundsOnClick;

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.MARKER_CLUSTER_GROUP.getName();
    }

    @Override
    public String toString() {
        return '{' +
                "spiderfyOnMaxZoom: " + spiderfyOnMaxZoom +
                ", showCoverageOnHover: " + showCoverageOnHover +
                ", zoomToBoundsOnClick: " + zoomToBoundsOnClick +
                '}';
    }

    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), "(", this.toString(), ");"));

        getMapView().addLayer(this);
    }

    /**
     * Добавление слоя в кластер
     * @param layer слой на добавление в кластер
     */
    public void addLayer(Layer layer) {
        log.info("Добавление в кластер слоя layer: {}", layer.getId());
        getMapView().execScript(this.getId() + ".addLayer(" + layer.getId() + ");");
    }

    /**
     * Удаление слоя из кластера
     * @param layer слой на удаление из кластера
     */
    public void removeLayer(Layer layer) {
        log.info("Удаление из кластера слоя layer: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove(" + layer.getId() + ");");
    }

    /**
     * Удаление всех слоев из кластера
     */
    public void clearLayers() {
        log.info("Удаление из кластера всех слоев");
        getMapView().execScript(this.getId() + ".clearLayers();");
    }
}
