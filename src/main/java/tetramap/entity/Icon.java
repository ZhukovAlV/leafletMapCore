package tetramap.entity;

/**
 * Иконка для маркера
 */
public interface Icon {

    /**
     * Получение URL иконки
     *
     * @return URL иконки
     */
    String getIconUrl();

    /**
     * Устанавка URL иконки
     */
    void setIconUrl(String url);
}
