package tetramap.entity.vectors;

/**
 * Интерфейс для классов где можно использовать стили
 *
 * @see PathOptions
 */
public interface HasStyle {

    PathOptions getStyle();

    void setStyle(PathOptions pathOptions);
}
