package tetramap.layer.groups;

import tetramap.entity.vectors.HasStyle;
import tetramap.entity.vectors.PathOptions;
import tetramap.layer.Layer;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;
import java.util.List;

public class FeatureGroup extends LayerGroup implements HasStyle {

    @Serial
    private static final long serialVersionUID = 4315847050612014255L;

    private PathOptions pathOptions = new PathOptions();

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

    @Override
    public void setStyle(PathOptions pathOptions) {
        this.pathOptions = pathOptions;
        this.getLayers().stream().filter(layer -> layer instanceof HasStyle).map(HasStyle.class::cast)
                .forEach(layer -> layer.setStyle(pathOptions));
    }

    @Override
    public PathOptions getStyle() {
        return pathOptions;
    }
}
