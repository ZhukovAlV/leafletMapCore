package tetramap.js;


import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

/**
 * Используется для выполнения js скриптов в leaflet
 */
public interface ExecutableFunctions extends Identifiable {

    default void executeJs(String functionName, Serializable... arguments) {
        executeJs(this, functionName, arguments);
    }

    void executeJs(Identifiable target, String functionName, Serializable... arguments);

    default <T extends Serializable> CompletableFuture<T> call(String functionName, Class<T> resultType,
                                                               Serializable... arguments) {
        return call(this, functionName, resultType, arguments);
    }

    <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName, Class<T> resultType,
                                                       Serializable... arguments);
}