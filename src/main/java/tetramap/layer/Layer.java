package tetramap.layer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.gui.LeafletMap;
import tetramap.js.LeafletObject;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Layer extends LeafletObject {

    public void addTo(LeafletMap leafletMap) {
        setParent(leafletMap);
        leafletMap.addLayer(this);
    }

/*    public String getLayerId() {


    }

    public void setLayerId(String layerId) {
    }*/
}
