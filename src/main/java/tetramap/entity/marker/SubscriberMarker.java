package tetramap.entity.marker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import tetramap.bitmap.BitmapType;
import tetramap.entity.types.LatLong;
import tetramap.event.MarkerEventListener;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Log4j2
public class SubscriberMarker extends Marker implements MarkerEventListener {

    /**
     * Выбран маркер или нет
     */
    private boolean selected;

    /**
     * Варианты картинок для иконок маркера в зависимости от статуса маркера
     */
    private BitmapType bitmapType;

    public SubscriberMarker(LatLong latLong, BitmapType bitmapType, String title, boolean selected) {
        this(latLong, bitmapType, title);
        this.selected = selected;
    }

    public SubscriberMarker(LatLong latLong, BitmapType bitmapType, String title) {
        this(latLong, bitmapType);
        setTitle(title);
    }

    public SubscriberMarker(LatLong latLong, BitmapType bitmapType) {
        super(latLong);
        this.bitmapType = bitmapType;

        setIcon(bitmapType.getBitmapUnknown().getIcon());
    }

    @Override
    public void onMarkerSelected(boolean selectedState) {
      //  System.out.println(this + " : " + selectedState);

        if (selectedState) setIcon(bitmapType.getBitmapSelect().getIcon());
            else setIcon(bitmapType.getBitmapUnknown().getIcon());

        this.updateTo();
    }
}
