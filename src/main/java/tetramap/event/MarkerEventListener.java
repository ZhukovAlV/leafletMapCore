package tetramap.event;

import tetramap.entity.marker.Marker;

import java.util.List;

/**
 * Слушатель выделения маркера на карте
 */
public interface MarkerEventListener {

    /**
     * Выполняется при выделении маркера абонента на карте
     * @param marker маркер, который изменил состояние
     * @param selectedState новое состояние - выделен / не выделен
     */
    void onMarkerSelected(Marker marker, boolean selectedState);

    /**
     * Выполняется при выделении списка маркеров абонентов на карте
     * @param markers список маркеров, которые изменили состояние
     * @param selectedState новое состояние - выделен / не выделен
     */
    void onMarkersSelected(List<Marker> markers, boolean selectedState);
}
