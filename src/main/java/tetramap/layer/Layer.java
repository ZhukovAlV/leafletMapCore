package tetramap.layer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tetramap.gui.LeafletMap;
import tetramap.js.LeafletObject;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Layer extends LeafletObject {

    private static final long serialVersionUID = -1803411573095089760L;

    public void addTo(LeafletMap leafletMap) {
      //  setParent(leafletMap);
        leafletMap.addLayer(this);
    }


}
