package tetramap.gui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lombok.extern.log4j.Log4j2;
import tetramap.adapter.*;
import tetramap.bitmap.BitmapType;
import tetramap.bitmap.SubscriberBitmapType;
import tetramap.draw.*;
import tetramap.entity.marker.SubscriberMarker;
import tetramap.entity.popup.Popup;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.structure.LatLongArray;
import tetramap.event.LabelLatLong;

import java.io.InputStream;
import java.util.Arrays;

/**
 * Реализованная панель для JavaFx
 */
@Log4j2
public class MapPaneJavaFX extends AnchorPane implements MapPane {

    // Карта
    private final MapView mapView;

    // Ширина отступа элементов от края панели
    private static final int BORDER_SIZE = 15;

    // Отступ для панели (zoom panel)
    private static final double ZOOM_BUTTON_INDENT = 30;
    // Отступ между кнопками (zoom panel)
    private static final int ZOOM_BUTTON_GAP = 10;
    // Размер кнопки (zoom panel)
    private static final int ZOOM_BUTTON_SIZE = 50;
    // Размер иконки на кнопке (zoom panel)
    private static final int ZOOM_ICON_SIZE = ZOOM_BUTTON_SIZE - 4;

    // Отступ между кнопками (selection panel)
    private static final int BUTTON_GAP = 10;
    // Размер кнопки (selection panel)
    private static final int BUTTON_SIZE = 40;
    // Размер иконки на кнопке (selection panel)
    private static final int ICON_SIZE = BUTTON_SIZE - 4;
    // Количество кнопок в панели (selection panel)
    private static int buttonCount;

    // Отступ для панели выбора карт
    private static final double TILE_SOURCE_INDENT = 20;
    // Панель выбора карт
    private static final int TILE_SOURCE_COMBO_BOX_WIDTH = 250;
    // Размер кнопок на панели инструментов
    private static final int TILE_SOURCE_COMBO_BOX_HEIGHT = 25;

    // Элемент выбора тайловой подложки
    private final ComboBox<Object> tileSourceComboBox = new ComboBox<>();

    // Файловый менеджер и парсер для загрузки/сохранения области выделения
    private static final FileChooser fileChooser = new FileChooser();
    //private static final SelectionGeojsonParser selectionGeoJsonParser = new SelectionGeojsonParser();
    private static final String EXTENSION_FILE_GEO_JSON = ".geojson";

    // Кнопки управления масштабом
    private final VBox zoomBox = new VBox();
    private final Button centerButton = new Button();
    private final Button zoomInButton = new Button();
    private final Button zoomOutButton = new Button();

    // Кнопки для работы с объектами на карте
    private final VBox selectionBox = new VBox();
    private final Button boxSelectionButton = new Button();
    private final Button circleSelectionButton = new Button();
    private final Button polygonSelectionButton = new Button();
    private final Button rulerToggleButton = new Button();
    private final Button routeToggleButton = new Button();
    private final Button repeatSelectionButton = new Button();
    private final Button cancelSelectionButton = new Button();

    // Кнопки сохранения / загрузки области выделения
    private final Button loadSelectionButton = new Button();
    private final Button saveSelectionButton = new Button();

    private final LabelLatLong textLatLong = new LabelLatLong();

    // Адаптер для прорисовки маршрута Graphhopper
    private RouteDrawAdapter routeDrawAdapter;

    // Адаптер для измерения расстояния маршрута
    private RulerDrawAdapter rulerDrawAdapter;

    // Адаптер для рисования прямоугольника
    private RectangleDrawAdapter rectangleDrawAdapter;

    // Адаптер для рисования многоугольника
    private PolygonDrawAdapter polygonDrawAdapter;

    // Адаптер для рисования круга
    private CircleDrawAdapter circleDrawAdapter;

    public MapPaneJavaFX(MapView mapView) {
        super();
        this.mapView = mapView;

        initialize();
    }

    @Override
    public void initialize() {

        // Инициализируем адаптеры
        routeDrawAdapter = new RouteDrawAdapterImpl(mapView);
        rulerDrawAdapter = new RulerDrawAdapterImpl(mapView);
        rectangleDrawAdapter = new RectangleDrawAdapterImpl(mapView);
        polygonDrawAdapter = new PolygonDrawAdapterImpl(mapView);
        circleDrawAdapter = new CircleDrawAdapterImpl(mapView);

        // Добавляем карту на отображение в панели
        getChildren().add((Node)mapView);

        // Загрузка иконок для кнопок
        try {
            centerButton.setGraphic(loadIcon("icon/button/center.png", ZOOM_ICON_SIZE));
            zoomInButton.setGraphic(loadIcon("icon/button/zoom-in.png", ZOOM_ICON_SIZE));
            zoomOutButton.setGraphic(loadIcon("icon/button/zoom-out.png", ZOOM_ICON_SIZE));
            boxSelectionButton.setGraphic(loadIcon("icon/button/box-selection.png", ICON_SIZE));
            circleSelectionButton.setGraphic(loadIcon("icon/button/round-selection.png", ICON_SIZE));
            polygonSelectionButton.setGraphic(loadIcon("icon/button/polygon-selection.png", ICON_SIZE));
            repeatSelectionButton.setGraphic(loadIcon("icon/button/reload-selection.png", ICON_SIZE));
            cancelSelectionButton.setGraphic(loadIcon("icon/button/cancel-selection.png", ICON_SIZE));
            loadSelectionButton.setGraphic(loadIcon("icon/button/load.png", ICON_SIZE));
            saveSelectionButton.setGraphic(loadIcon("icon/button/save.png", ICON_SIZE));
            rulerToggleButton.setGraphic(loadIcon("icon/button/ruler.png", ICON_SIZE));
            routeToggleButton.setGraphic(loadIcon("icon/button/trace_path.png", ICON_SIZE));
        } catch (NullPointerException e) {
            log.warn("Ошибка при загрузке иконок для кнопок управления карты!");
        }

        // Отключаем кнопки для сохранения и открытия файла geoJson
       // repeatSelectionButton.setDisable(true);
        saveSelectionButton.setDisable(true);

        // Добавляем Label с координатами
        AnchorPane.setRightAnchor(textLatLong, BORDER_SIZE * 2.0);
        AnchorPane.setBottomAnchor(textLatLong, BORDER_SIZE * 2.0);
        getChildren().add(textLatLong);
        // Добавляем ее как слушателя на перемещение мыши
        mapView.addMouseMoveListener(textLatLong);

        // Задаем размеры кнопкам и расположение
        for (Button button : Arrays.asList(zoomInButton, zoomOutButton,
                centerButton)) {
            button.setMinSize(ZOOM_BUTTON_SIZE, ZOOM_BUTTON_SIZE);
            button.setPrefSize(ZOOM_BUTTON_SIZE, ZOOM_BUTTON_SIZE);
            button.setMaxSize(ZOOM_BUTTON_SIZE, ZOOM_BUTTON_SIZE);

            zoomBox.getChildren().add(button);
        }
        zoomBox.setSpacing(ZOOM_BUTTON_GAP);
        AnchorPane.setLeftAnchor(zoomBox, (double)BORDER_SIZE);
        AnchorPane.setTopAnchor(zoomBox, ZOOM_BUTTON_INDENT);

        tileSourceComboBox.setMinSize(TILE_SOURCE_COMBO_BOX_WIDTH, TILE_SOURCE_COMBO_BOX_HEIGHT);
        tileSourceComboBox.setPrefSize(TILE_SOURCE_COMBO_BOX_WIDTH, TILE_SOURCE_COMBO_BOX_HEIGHT);
        tileSourceComboBox.setMaxSize(TILE_SOURCE_COMBO_BOX_WIDTH, TILE_SOURCE_COMBO_BOX_HEIGHT);
        AnchorPane.setRightAnchor(tileSourceComboBox, (double)BORDER_SIZE);
        AnchorPane.setTopAnchor(tileSourceComboBox, TILE_SOURCE_INDENT);

        for (Button button : Arrays.asList(rulerToggleButton, routeToggleButton, boxSelectionButton,
                circleSelectionButton, polygonSelectionButton, repeatSelectionButton,
                cancelSelectionButton, saveSelectionButton, loadSelectionButton)) {
            button.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
            button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
            button.setMaxSize(BUTTON_SIZE, BUTTON_SIZE);

            selectionBox.getChildren().add(button);
            buttonCount += 1;
        }
        selectionBox.setSpacing(BUTTON_GAP);
        updatePanelHeight();

        // Добавляем панели для отображения
        getChildren().addAll(zoomBox, selectionBox);

        // Слушатель на изменение окна
        heightProperty().addListener((obs, oldVal, newVal) -> {
            updatePanelHeight();
            resizeMap();
        });

        widthProperty().addListener((obs, oldVal, newVal) -> resizeMap());

        // Установка слушателей масштаба карты
        zoomInButton.setOnAction(event -> mapView.zoomIn());
        zoomOutButton.setOnAction(event -> mapView.zoomOut());

        // Слушатель на центр карты
        centerButton.setOnAction(event -> mapView.moveToCenter());

        rulerToggleButton.setOnAction(event -> {
            clearDrawEvent();

            rulerDrawAdapter.onInvoke();
        });

        // Слушатель на построение маршрута по заданным точкам
        routeToggleButton.setOnAction(event -> {
            clearDrawEvent();

            if (((LatLongArray)routeDrawAdapter.getLatLongPolyline().getLatLongs()).isEmpty()) routeDrawAdapter.onInvoke();
                else routeDrawAdapter.onRevoke();
        });

        circleSelectionButton.setOnAction(event -> {
            clearDrawEvent();

            circleDrawAdapter = new CircleDrawAdapterImpl(mapView);
            circleDrawAdapter.onInvoke();
        });

        polygonSelectionButton.setOnAction(event -> {
            clearDrawEvent();

            polygonDrawAdapter = new PolygonDrawAdapterImpl(mapView);
            polygonDrawAdapter.onInvoke();
        });

        boxSelectionButton.setOnAction(event -> {
            clearDrawEvent();

            rectangleDrawAdapter = new RectangleDrawAdapterImpl(mapView);
            rectangleDrawAdapter.onInvoke();
        });

        repeatSelectionButton.setOnAction(event -> {
            clearDrawEvent();

            BitmapType subscriberBitmapType = new SubscriberBitmapType(mapView);

            // Добавим маркер N раз
            LatLong latLong = new LatLong(55.040, 73.2695);
            for (int i = 0; i < 10; i++) {
                latLong = new LatLong(latLong.getLatitude() + 0.001, latLong.getLongitude() + 0.001);
                // Marker marker = new Marker(latLong, icon, "Marker №" + i);

                SubscriberMarker marker = new SubscriberMarker(latLong, subscriberBitmapType, "Marker №" + i);
                mapView.getLayerGroup().addLayer(marker);

                mapView.getMarkerManager().addMarker(marker);

                // Если кластеры использовать
/*            mapView.execScript("var " +  marker.getId() + " = L.marker(" + marker + ");");
            mapView.execScript("markers.addLayer(" + marker.getId() + ");");
            marker.setMapView(mapView);
            marker.addTo(mapView);*/

                //marker.bindTooltip("Тестовый маркер №" + i);

                Popup popup = new Popup("Тестовый маркер №" + i);
                popup.addTo(mapView);

                // Добавляем подпись маркеру
                marker.bindPopup(popup);
            }
        });

        cancelSelectionButton.setOnAction(event -> clearDrawEvent());
    }

    /**
     * Очищаем все фигуры
     */
    private void clearDrawEvent() {
        routeDrawAdapter.onRevoke();
        rulerDrawAdapter.onRevoke();
        rectangleDrawAdapter.onRevoke();
        polygonDrawAdapter.onRevoke();
        circleDrawAdapter.onRevoke();
    }

    /**
     * Загружает и возвращает иконку из файла с указанным путем и измененным размером size x size
     * @param path путь к файлу с иконкой
     * @param size размер иконки
     * @return возвращает иконку из файла с указанным путем и измененным размером size x size
     */
    private static ImageView loadIcon(String path, int size) {
        return loadIcon(path, size, size);
    }

    /**
     * Загружает и возвращает иконку из файла с указанным путем и измененным размером width x height
     * @param path путь к файлу с иконкой
     * @param width ширина возвращаемой иконки
     * @param height высота возвращаемой иконки
     * @return возвращает иконку из файла с указанным путем и измененным размером width x height
     */
    private static ImageView loadIcon(String path, int width, int height) {
        InputStream resource = MapPaneJavaFX.class.getClassLoader().getResourceAsStream(path);
        if (resource == null) return null;

        Image image = new Image(resource);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    /**
     * Изменение расположения панели с кнопками
     */
    private void updatePanelHeight() {
        // Установка положения панели с элементами выделения
        double y = (getHeight() - (buttonCount * (BUTTON_SIZE + BUTTON_GAP))) / 2;
        AnchorPane.setRightAnchor(selectionBox, (double)BORDER_SIZE);
        AnchorPane.setTopAnchor(selectionBox, y);
    }

    /**
     * Масштабирование карты
     */
    private void resizeMap() {
        mapView.setSize(getWidth(), getHeight());
    }
}