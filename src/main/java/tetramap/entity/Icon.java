package tetramap.entity;

public interface Icon extends BasicType {

    /**
     * Возвращает URL иконки
     *
     * @return URL иконки
     */
    String getIconUrl();

    /**
     * Устанавливает URL иконки
     */
    void setIconUrl(String url);
}
