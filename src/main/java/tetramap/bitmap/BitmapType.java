package tetramap.bitmap;

import tetramap.entity.types.Icon;

/**
 * Интерфейс для иконки (маркера) с путем до файла с иконкой
 */
public interface BitmapType {

    /**
     * Возвращает путь файла иконки
     * @return маркер абонента
     */
    Icon getIcon();

    /**
     * Выбранный на карте маркер
     * @return маркер абонента
     */
    BitmapType getBitmapSelect();

    /**
     * Маркер абонента онлайн, не находящегося в движении
     * @return маркер абонента
     */
    BitmapType getBitmapUnknown();

    /**
     * Маркер абонента онлайн с устаревшими данными о местоположении
     * @return маркер абонента
     */
    BitmapType getBitmapOnline();

    /**
     * Маркер абонента оффлайн
     * @return маркер абонента
     */
    BitmapType getBitmapOffline();

    /**
     * Маркер абонента в экстренном режиме
     * @return маркер абонента
     */
    BitmapType getBitmapAlarm();
}