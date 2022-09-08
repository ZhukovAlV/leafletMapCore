package tetramap.function;

import tetramap.entity.Icon;
import tetramap.entity.LatLong;
import tetramap.map.functions.ExecutableFunctions;

import java.util.concurrent.CompletableFuture;

public interface MarkerFunctions extends ExecutableFunctions {

    /**
     * Добавление маркера в список
     *
     * @param marker маркер
     */
/*    void addMarker(Marker marker);*/

    /**
     * Удаление маркера из списка
     *
     * @param marker маркер
     */
/*    void removeMarker(Marker marker);*/

    /**
     * Получение координат маркера
     *
     * @return текущая позиция маркера
     */
    default CompletableFuture<LatLong> callLatLng() {
        return call("getLatLng", LatLong.class);
    }

    /**
     * Изменение положения маркера
     *
     * @param latLng новая позиция маркера
     */
    default void setLatLng(LatLong latLng) {
        executeJs("setLatLng", latLng);
    }

    /**
     * Возвращает иконку маркера
     *
     * @return текущая иконка маркера
     */
    Icon getIcon();

    /**
     * Устанавливает иконку маркеру
     *
     * @param icon иконка для маркера
     */
    default void setIcon(Icon icon) {
        executeJs("setIcon", icon);
    }
}
