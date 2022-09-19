package tetramap.entity;

/**
 * Иконка для маркера
 */
public interface Icon {

    /**
     * Устанавка URL иконки
     */
    void setIconUrl(String url);

    /**
     * Получение URL иконки
     *
     * @return URL иконки
     */
    String getIconUrl();
}
