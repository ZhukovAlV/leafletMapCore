package tetramap.layer.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Позволяет группировать слои в группы и работать с ними как с одним объектом.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Log4j2
public class LayerGroup extends Layer implements LayerGroupFunctions {

    @Serial
    private static final long serialVersionUID = 439247482151898231L;

    private List<Layer> layers;

    public LayerGroup() {
        super();
        this.layers = new ArrayList<>();
    }

    public LayerGroup(Layer... layers) {
        this(Arrays.asList(layers));
    }

    public LayerGroup(List<Layer> layers) {
        super();
        this.layers = layers;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void addLayer(Layer layer) {
        log.info("Добавляем слой на карту: {}", "id: " + layer.getId());
        layer.addTo(getMapView());

        log.info("Добавляем слой в группу: {}", "id: " + this.getId());
        this.getLayers().add(layer);
    }

    @Override
    public void removeLayer(Layer layer) {
        log.info("Удаляем слой с карты: {}", "id: " + layer.getId());
        layer.remove();

        log.info("Удаляем слой из группы: {}", "id: " + this.getId());
        getLayer(layer.getId()).ifPresent(l -> getLayers().remove(l));
    }

    @Override
    public void removeLayer(String layerId) {
        getLayer(layerId).ifPresent(l -> {
            log.info("Удаляем слой с карты: {}", "id: " + layerId);
            l.remove();

            log.info("Удаляем слой из группы: {}", "id: " + this.getId());
            getLayers().remove(l);
        });
    }

    @Override
    public boolean hasLayer(Layer layer) {
        log.info("Проверяется содержание слоя в группе {}", "id слоя: " + layer.getId()
                + "id группы: " + this.getId());
        return hasLayer(layer.getId());
    }

    @Override
    public boolean hasLayer(String layerId) {
        log.info("Проверяется содержание слоя в группе {}", "id слоя: " + layerId
                + "id группы: " + this.getId());
        return getLayer(layerId).isPresent();
    }

    @Override
    public void clearLayers() {
        log.info("Удаляются все слои из группы: {}", "id: " + this.getId());
        this.layers.forEach(l -> {
            log.info("Удаляем слой из группы: {}", "id: " + l.getId());
            l.remove();
        });
        this.layers.clear();
    }

    /**
     * Возвращает слой с предоставленным ID.
     *
     * @param layerId ID слоя
     * @return найденный слой с представленным layerId.
     */
    public Optional<Layer> getLayer(String layerId) {
        log.info("Производится поиск слоя в группе: {}", "id слоя: " + layerId
                + "id группы: " + this.getId());
        return this.layers.stream().filter(layer -> layer.getId().equals(layerId)).findFirst();
    }

    public Optional<Layer> findLayer(String layerId) {
        Optional<Layer> result = Optional.empty();
        if (this.getId().equals(layerId)) {
            return Optional.of(this);
        } else {
            for (Layer child : layers) {
                if (child instanceof LayerGroup) {
                    result = ((LayerGroup) child).findLayer(layerId);
                    if (result.isPresent()) {
                        break;
                    }
                } else if (child.getId().equals(layerId)) {
                    result = Optional.of(child);
                }
            }
        }
        return result;
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.LAYER_GROUP.getName();
    }
}
