package tetramap.event;

import tetramap.entity.types.LatLong;

public interface MapLeftClickEventListener {

    /**
     * Нажатие левой кнопки мыши по карты
     * @param latLong координата LatLong
     */
    void leftMouseClicked(LatLong latLong);
}
