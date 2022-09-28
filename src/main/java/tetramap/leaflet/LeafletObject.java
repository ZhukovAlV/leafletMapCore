package tetramap.leaflet;

import lombok.Data;

import java.util.UUID;

/**
 * Родительский класс объекта Leaflet
 */
@Data
public abstract class LeafletObject implements LeafletBasicType {

    /**
     * Генерируется уникальный ID
     */
    private String id = getLeafletType() + "_" + UUID.randomUUID().toString().replaceAll("-", "_");

    @Override
    public String getId() {
        return id;
    }
}
