package tetramap.entity.marker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import tetramap.bitmap.BitmapType;
import tetramap.entity.types.LatLong;
import tetramap.event.MapPopupEventListener;
import tetramap.event.MarkerEventListener;
import tetramap.gui.MapView;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Log4j2
public class SubscriberMarker extends Marker implements MarkerEventListener, MapPopupEventListener {

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

    /**
     * Добавления слоя на карту
     */
    @Override
    public void addTo(MapView mapView) {
        setMapView(mapView);

        log.info("Создание на карте объекта {}", this.getId());
        mapView.execScript(String.join("",this.getId(), " = L.", this.getTypeInstantiatesMap(), "(", this.toString(), ");"));

        log.info("Выставление id объекту: {}", this.getId());
        mapView.execScript(String.join("",this.getId(), "._objId = '", this.getId(), "';"));
    }

    /**
     * Обновление слоя на карте
     */
    @Override
    public void updateTo() {
        getMapView().getMarkerManager().getMarkerCluster().removeLayer(this);
        this.remove();
        this.addTo(getMapView());
        if (this.getPopup() != null) this.bindPopup(this.getPopup());
        getMapView().getMarkerManager().getMarkerCluster().addLayer(this);
    }

    @Override
    public void remove(){
        log.info("Удаление с карты объекта: {}", this.getId());
        getMapView().execScript(this.getId() + ".remove();");
    }

    @Override
    public void onMarkerSelected(boolean selectedState) {
      //  System.out.println(this + " : " + selectedState);

        if (selectedState) setIcon(bitmapType.getBitmapSelect().getIcon());
            else setIcon(bitmapType.getBitmapUnknown().getIcon());

        this.updateTo();
    }

    @Override
    public void popupOpen() {
        log.info("Открыто всплывающее окно у объекта: {}", this.getId());
    }

    @Override
    public void popupClose() {
        log.info("Закрыто всплывающее окно у объекта: {}", this.getId());
    }
    
}
