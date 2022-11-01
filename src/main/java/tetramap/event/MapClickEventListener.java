package tetramap.event;

import tetramap.entity.types.LatLong;

public interface MapClickEventListener {

    /**
     * Нажатие мыши по карты
     * @param latLong координата LatLong
     */
    void mouseClicked(LatLong latLong);
}
