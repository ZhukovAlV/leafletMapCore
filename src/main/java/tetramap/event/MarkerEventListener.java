package tetramap.event;

/**
 * Слушатель выделения маркера на карте
 */
public interface MarkerEventListener {

    /**
     * Выполняется при выделении маркера абонента на карте
     * @param selectedState новое состояние - выделен / не выделен
     */
    void onMarkerSelected(boolean selectedState);
}
