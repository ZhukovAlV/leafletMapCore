package tetramap.config;

public enum ControlPosition {

    TOP_LEFT("topleft"),
    TOP_RIGHT("topright"),
    BOTTOM_LEFT("bottomleft"),
    BOTTOM_RIGHT("bottomright");

    private final String positionName;

    ControlPosition(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }
}
