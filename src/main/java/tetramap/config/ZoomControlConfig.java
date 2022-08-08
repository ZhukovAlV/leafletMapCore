package tetramap.config;

public class ZoomControlConfig {
    private final boolean show;
    private final ControlPosition position;

    public ZoomControlConfig(boolean show, ControlPosition position) {
        this.show = show;
        this.position = position;
    }

    public boolean isShow() {
        return show;
    }

    public ControlPosition getPosition() {
        return position;
    }
}
