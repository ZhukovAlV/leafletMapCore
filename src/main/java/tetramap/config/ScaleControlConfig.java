package tetramap.config;

public class ScaleControlConfig {
    private final boolean show;
    private final ControlPosition position;
    private final boolean metric;

    public ScaleControlConfig(boolean show, ControlPosition position, boolean metric) {
        this.show = show;
        this.position = position;
        this.metric = metric;
    }

    public boolean isShow() {
        return show;
    }

    public ControlPosition getPosition() {
        return position;
    }

    public boolean isMetric() {
        return metric;
    }
}
