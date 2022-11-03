package tetramap.layer.groups;

import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.List;

public class FeatureGroup extends LayerGroup {

    @Serial
    private static final long serialVersionUID = 4315847050612014255L;
    //private PathOptions pathOptions = new PathOptions();

    public FeatureGroup() {
        super();
    }

    public FeatureGroup(final Layer... layers) {
        super(layers);
    }

    public FeatureGroup(final List<Layer> layers) {
        super(layers);
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.FEATURE_GROUP.getName();
    }
}
