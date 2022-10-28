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
}
