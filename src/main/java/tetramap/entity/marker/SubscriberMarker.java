package tetramap.entity.marker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.types.Icon;
import tetramap.entity.types.LatLong;
import tetramap.event.MarkerEventListener;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Log4j2
public class SubscriberMarker extends Marker implements MarkerEventListener {

    private boolean selected;

    public SubscriberMarker(LatLong latLong, Icon icon, String title, boolean selected) {
        super(latLong, icon, title);
        this.selected = selected;
    }

    public SubscriberMarker(LatLong latLong, Icon icon, String title) {
        super(latLong, icon, title);
    }

    public SubscriberMarker(LatLong latLong, String title) {
        super(latLong, title);
    }

    public SubscriberMarker(LatLong latLong) {
        super(latLong);
    }

    @Override
    public void onMarkerSelected(boolean selectedState) {
        // TODO доделать смену иконки у маркера, когда его выбирают на карте
        System.out.println(this + " : " + selectedState);

        Icon iconRed = new Icon(getClass().getResource("/icon/marker/subscriber/marker_red.png").getPath());
        iconRed.addTo(getMapView());

        Icon iconGreen = new Icon(getClass().getResource("/icon/marker/subscriber/marker_green.png").getPath());
        iconGreen.addTo(getMapView());

        if (selectedState) setIcon(iconRed);
        else setIcon(iconGreen);

        this.updateTo();
    }
}
