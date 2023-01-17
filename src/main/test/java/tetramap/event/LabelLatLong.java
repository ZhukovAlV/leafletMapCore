package tetramap.event;

import javafx.scene.control.Label;
import tetramap.entity.types.LatLong;

import java.text.DecimalFormat;

public class LabelLatLong extends Label implements MapMoveEventListener {

    // Широта
    private final Label latLabel;
    // Долгота
    private final Label longLabel;

    // Формат вывода координат
    private CoordinateFormat coordinateFormat;
    // Формат для широты
    private final DecimalFormat latFormatter = new DecimalFormat("00.000000");
    // Формат для долготы
    private final DecimalFormat lonFormatter = new DecimalFormat("000.000000");

    public LabelLatLong() {
        this(new Label("0"), new Label("0"), CoordinateFormat.ANGLE);
    }

    public LabelLatLong(Label latLabel, Label longLabel, CoordinateFormat coordinateFormat) {
        this(latLabel, longLabel);
        this.coordinateFormat = coordinateFormat;
    }

    public LabelLatLong(Label latLabel, Label longLabel) {
        this.latLabel = latLabel;
        this.longLabel = longLabel;
        this.coordinateFormat = CoordinateFormat.ANGLE;
    }

    @Override
    public void mouseMoved(LatLong latLong) {
        setLatLong(latLong);
        setText(latLabel.getText() + " " + longLabel.getText());
    }

    /**
     * Устанавливает точку, координаты которой отображаются панелью
     * @param latLong гео-точка
     */
    public void setLatLong(LatLong latLong) {
        try {
            double lat = latLong.getLatitude();
            double lon = latLong.getLongitude();
            // Форматирование вывода
            if (coordinateFormat == CoordinateFormat.DECIMAL) {
                latLabel.setText(latFormatter.format(Math.abs(lat)) + (lat > 0 ? " c.ш." : " ю.ш."));
                longLabel.setText(lonFormatter.format(Math.abs(lon)) + (lon > 0 ? " в.д." : " з.д."));
            } else {
                int latDegree = (int) Math.abs(lat);
                int latMinute = (int) ((Math.abs(lat) - latDegree) * 60);
                int latSecond = (int) (((Math.abs(lat) - latDegree) -  (latMinute / 60d)) * 3600);
                int lonDegree = (int) lon;
                int lonMinute = (int) ((Math.abs(lon) - lonDegree) * 60);
                int lonSecond = (int) (((Math.abs(lon) - lonDegree) -  (lonMinute / 60d)) * 3600);

                latLabel.setText((lat >= 0 ? "N" : "S") + String.format(" %02d\u00B0 %02d\' %02d\"", latDegree, latMinute, latSecond));
                longLabel.setText((lon >= 0 ? "E" : "W") + String.format(" %03d\u00B0 %02d\' %02d\"", lonDegree, lonMinute, lonSecond));
            }

        } catch (NullPointerException ex) {
            // Точка за пределами карты
            latLabel.setText("За границами");
            longLabel.setText("За границами");
        }
    }

    /**
     * Формат представления координат
     */
    public enum CoordinateFormat {
        // Десятичный
        DECIMAL,
        // Углы, минуты, секунды
        ANGLE;

        public static CoordinateFormat getByInt(int i) {
            return i == 1 ? DECIMAL : ANGLE;
        }
    }
}