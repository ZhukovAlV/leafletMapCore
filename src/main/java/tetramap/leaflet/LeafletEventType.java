package tetramap.leaflet;

public interface LeafletEventType {
    default String getLeafletEvent() {
        return name();
    }

    String name();
}
