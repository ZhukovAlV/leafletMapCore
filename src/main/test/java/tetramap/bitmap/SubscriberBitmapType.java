package tetramap.bitmap;

import tetramap.entity.types.Icon;
import tetramap.gui.MapView;

public class SubscriberBitmapType implements BitmapType {

    private MapView mapView;

    // Выбранный на карте маркер
    private static final BitmapType SELECTED = new SubscriberBitmapType(new Icon(SubscriberBitmapType.class.getResource("/icon/marker/subscriber/marker_red.png").getPath()));
    // Маркер абонента онлайн, не находящегося в движении
    private static final BitmapType UNKNOWN = new SubscriberBitmapType(new Icon(SubscriberBitmapType.class.getResource("/icon/marker/subscriber/marker_yellow.png").getPath()));
    // Маркер абонента онлайн с устаревшими данными о местоположении
    private static final BitmapType ONLINE = new SubscriberBitmapType(new Icon(SubscriberBitmapType.class.getResource("/icon/marker/subscriber/marker_green.png").getPath()));
    // Маркер абонента оффлайн
    private static final BitmapType OFFLINE = new SubscriberBitmapType(new Icon(SubscriberBitmapType.class.getResource("/icon/marker/subscriber/marker_gray.png").getPath()));
    // Маркер абонента в экстренном режиме
    private static final BitmapType ALARM = new SubscriberBitmapType(new Icon(SubscriberBitmapType.class.getResource("/icon/marker/subscriber/marker_darkred.png").getPath()));

    // Путь до иконки
    private Icon icon;

    private SubscriberBitmapType(Icon icon) {
        this.icon = icon;
    }

    public SubscriberBitmapType(MapView mapView) {
        this.mapView = mapView;

        SELECTED.getIcon().addTo(mapView);
        UNKNOWN.getIcon().addTo(mapView);
        ONLINE.getIcon().addTo(mapView);
        OFFLINE.getIcon().addTo(mapView);
        ALARM.getIcon().addTo(mapView);
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public BitmapType getBitmapSelect() {
        return SELECTED;
    }

    @Override
    public BitmapType getBitmapUnknown() {
        return UNKNOWN;
    }

    @Override
    public BitmapType getBitmapOnline() {
        return ONLINE;
    }

    @Override
    public BitmapType getBitmapOffline() {
        return OFFLINE;
    }

    @Override
    public BitmapType getBitmapAlarm() {
        return ALARM;
    }
}
