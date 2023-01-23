package tetramap.adapter;

/**
 * Интерфейс для подключаемых / отключаемых куда-либо объектов
 */
public interface Invokable {

    /**
     * Выполняется при подключении объекта
     */
    void onInvoke();

    /**
     * Выполняется при отключении объекта
     */
    void onRevoke();

    /**
     * Проверяет, подключен ли переданный слушатель к карте
     */
    boolean isInvoked();

    /**
     * Отключает слушателей
     */
    void removeListeners();
}
