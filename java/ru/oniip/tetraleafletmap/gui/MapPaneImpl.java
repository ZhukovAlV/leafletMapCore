package ru.oniip.tetraleafletmap.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;
import ru.oniip.tetraleafletmap.adapters.CircleDrawAdapter;
import ru.oniip.tetraleafletmap.event.MapEvents;

import java.io.InputStream;
import java.util.Arrays;

@Log4j2
public class MapPaneImpl extends AnchorPane implements MapPane {

    // Карта
    private final LeafletMapView mapView = new LeafletMapView();

    // Ширина отступа элементов от края панели
    public static final int BORDER_SIZE = 15;

    // Отступ для панели (zoom panel)
    public static final double ZOOM_BUTTON_INDENT = 30;
    // Отступ между кнопками (zoom panel)
    public static final int ZOOM_BUTTON_GAP = 10;
    // Размер кнопки (zoom panel)
    public static final int ZOOM_BUTTON_SIZE = 50;
    // Размер иконки на кнопке (zoom panel)
    public static final int ZOOM_ICON_SIZE = ZOOM_BUTTON_SIZE - 4;

    // Отступ между кнопками (selection panel)
    public static final int BUTTON_GAP = 10;
    // Размер кнопки (selection panel)
    public static final int BUTTON_SIZE = 40;
    // Размер иконки на кнопке (selection panel)
    public static final int ICON_SIZE = BUTTON_SIZE - 4;
    // Количество кнопок в панели (selection panel)
    public static int buttonCount;

    // Отступ для панели выбора карт
    public static final double TILE_SOURCE_INDENT = 20;
    // Панель выбора карт
    public static final int TILE_SOURCE_COMBO_BOX_WIDTH = 250;
    // Размер кнопок на панели инструментов
    public static final int TILE_SOURCE_COMBO_BOX_HEIGHT = 25;

    // Элемент выбора тайловой подложки
    private final ComboBox<Object> tileSourceComboBox = new ComboBox<>();

    // Файловый менеджер и парсер для загрузки/сохранения области выделения
/*    private static final FileChooser fileChooser = new FileChooser();
    private static final SelectionGeojsonParser selectionGeoJsonParser = new SelectionGeojsonParser();
    private static final String EXTENSION_FILE_GEO_JSON = ".geojson";*/

    // Кнопки управления масштабом
/*    private final VBox zoomBox = new VBox();
    private final Button centerButton = new Button();
    private final Button zoomInButton = new Button();
    private final Button zoomOutButton = new Button();*/

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

    public MapPaneImpl() {
        super();
        initialize();
    }

    @Override
    public void initialize() {

        // Добавление карты
        getChildren().addAll(mapView);

 /*       // Square frame buffer
        Parameters.SQUARE_FRAME_BUFFER = false;

        // Добавляем фильтр для fileChooser для файлов geojson и директорию по умолчанию
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Name file(*" + EXTENSION_FILE_GEO_JSON + ")", "*" + EXTENSION_FILE_GEO_JSON);
        fileChooser.getExtensionFilters().add(extFilter);

        File userDirectory = mapPane.getMapPaneInitializer().getSelectionFile();
        if(userDirectory != null && userDirectory.canRead()) {
            fileChooser.setInitialDirectory(userDirectory);
        }


        // Выставляем список карт в панель выбора карт и отображаем первый по умолчанию
        Object[] listTileLayer = mapPane.getMapView().getTileLayersManager().getTileLayerMap().keySet().toArray();
        tileSourceComboBox.setItems(FXCollections.observableArrayList(listTileLayer));
        // Выставляем карту по умолчанию
        tileSourceComboBox.getSelectionModel().selectFirst();
        mapPane.getMapView().getTileLayersManager().setTileSource(listTileLayer[0]);

        // Слушатель на изменение масштаба
        mapPane.getMapView().addZoomObserver(this);*/

        // Слушатели на кнопки
/*
        zoomInButton.setOnAction(event -> mapPane.getMapView().getModel().mapViewPosition.zoomIn());
        zoomOutButton.setOnAction(event -> mapPane.getMapView().getModel().mapViewPosition.zoomOut());
        centerButton.setOnAction(event -> mapPane.getMapView().getModel().mapViewPosition.animateTo(mapPane.getMapView().getMapViewInitializer().getDefaultCenter()));

        boxSelectionButton.setOnAction(event -> mapPane.getMapView().getAdapterManager().invokeAdapter(mapPane.getRectangleDrawAdapter(), true));
        circleSelectionButton.setOnAction(event -> mapPane.getMapView().getAdapterManager().invokeAdapter(mapPane.getCircleDrawAdapter(), true));
        polygonSelectionButton.setOnAction(event -> mapPane.getMapView().getAdapterManager().invokeAdapter(mapPane.getPolygonDrawAdapter(), true));

        rulerToggleButton.setOnAction(event -> {
            if (rulerToggleButton.isFocused()) {
                mapPane.getMapView().getAdapterManager().invokeAdapter(mapPane.getRulerDrawAdapter(), true);
            } else {
                mapPane.getMapView().getAdapterManager().revokeAdapter(mapPane.getRulerDrawAdapter());
            }
        });

        routeToggleButton.setOnAction(event -> {
            if (routeToggleButton.isFocused()) {
                if (mapPane.getMapView().getRouteManager().isDataAvailable()) {
                    mapPane.getMapView().getAdapterManager().invokeAdapter(mapPane.getRouteDrawAdapter(), true);
                }
            } else {
                mapPane.getMapView().getAdapterManager().revokeAdapter(mapPane.getRouteDrawAdapter());
            }
        });

        repeatSelectionButton.setOnAction(event -> {
            // Повтор выбора маркеров в построенной области
            if (mapPane.getMapView().getSubscriberMarkerManager() != null) {

                for (MouseAdapter mouseAdapter : mapPane.getMapView().getAdapterManager().getCurrentAdapters()) {
                    if (mouseAdapter instanceof InvokableMouseAdapter) {
                        mapPane.getMapView().getSubscriberMarkerManager().selectMarkersInLayer(
                                ((InvokableMouseAdapter<? extends Layer>) mouseAdapter).getShapeLayer()
                        );
                    }
                }
            }
        });

        loadSelectionButton.setOnAction(event -> {
            // Загрузка области выделения из файла
            File openFile = fileChooser.showOpenDialog(null);
            if (openFile != null) {
                try {
                    // Генерация области выделения
                    Selection selection = selectionGeoJsonParser.parse(openFile);
                    // Загрузка на карту
                    loadSelection(selection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        saveSelectionButton.setOnAction(event -> {
            // Сохранение области выделения в файл
            File saveFile = fileChooser.showSaveDialog(null);
            if (saveFile != null) {

                // Дописываем расширение в конце файле, если его нет
                if(!saveFile.getName().contains(EXTENSION_FILE_GEO_JSON)) {
                    saveFile = new File(saveFile.getAbsolutePath() + EXTENSION_FILE_GEO_JSON);
                }

                // Тип фигуры определяется по нажатой кнопке
                // Осуществляется генерация модели области и сохранение ее в файл
                if (mapPane.getMapView().getAdapterManager().getCurrentAdapters().get(0) instanceof RectangleDrawAdapter) {

                    selectionGeoJsonParser.write(saveFile,
                            new Selection(Selection.SelectionType.RECTANGLE,
                                    mapPane.getRectangleDrawAdapter().getShapeLayer().getLatLongs()));

                } else if (mapPane.getMapView().getAdapterManager().getCurrentAdapters().get(0) instanceof CircleDrawAdapter) {

                    Circle circle = mapPane.getCircleDrawAdapter().getShapeLayer();

                    List<LatLong> latLongs = Collections.singletonList(circle.getPosition());
                    Selection selection = new Selection(Selection.SelectionType.CIRCLE, latLongs);
                    selection.setRadius(OptionalDouble.of(circle.getRadius()));

                    selectionGeoJsonParser.write(
                            saveFile,
                            selection
                    );

                } else if (mapPane.getMapView().getAdapterManager().getCurrentAdapters().get(0) instanceof PolygonDrawAdapter) {

                    selectionGeoJsonParser.write(saveFile,
                            new Selection(
                                    Selection.SelectionType.POLYGON,
                                    mapPane.getPolygonDrawAdapter().getShapeLayer().getLatLongs()
                            )
                    );
                }
            }
        });

        tileSourceComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            mapPane.getMapView().getTileLayersManager().setTileSource(tileSourceComboBox.getSelectionModel().getSelectedItem());
            onZoomChange(mapPane.getMapView().getModel().mapViewPosition.getZoomLevel());
        });
*/

        // Загрузка иконок для кнопок
        try {
/*            centerButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/center.png", ZOOM_ICON_SIZE));
            zoomInButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/zoom-in.png", ZOOM_ICON_SIZE));
            zoomOutButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/zoom-out.png", ZOOM_ICON_SIZE));*/
            boxSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/box-selection.png", ICON_SIZE));
            circleSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/round-selection.png", ICON_SIZE));
            polygonSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/polygon-selection.png", ICON_SIZE));
            repeatSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/reload-selection.png", ICON_SIZE));
            cancelSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/cancel-selection.png", ICON_SIZE));
            loadSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/load.png", ICON_SIZE));
            saveSelectionButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/save.png", ICON_SIZE));
            rulerToggleButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/ruler.png", ICON_SIZE));
            routeToggleButton.setGraphic(loadIcon("ru/oniip/tetraleafletmap/buttonIcon/trace_path.png", ICON_SIZE));
        } catch (NullPointerException e) {
            log.warn("Ошибка при загрузке иконок для кнопок управления карты!");
        }

        // Отключаем кнопки для сохранения и открытия файла geoJson
        repeatSelectionButton.setDisable(true);
        saveSelectionButton.setDisable(true);

/*
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
*/

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
        //  getChildren().addAll(zoomBox, selectionBox, tileSourceComboBox);
        getChildren().addAll(selectionBox, tileSourceComboBox);

        // Добавляем слушатели на слои
/*        mapPane.getRectangleDrawAdapter().addMapLayerObserver(this);
        mapPane.getCircleDrawAdapter().addMapLayerObserver(this);
        mapPane.getPolygonDrawAdapter().addMapLayerObserver(this);*/

        // Выставляем видимость понелям в зависимости от настроек
/*        getTileSourceComboBox().setVisible(mapPane.getMapPaneInitializer().getTileSourceSelection());*/

        // Слушатель на изменение окна
        heightProperty().addListener((obs, oldVal, newVal) -> {
            updatePanelHeight();
            resizeMap();
        });

        widthProperty().addListener((obs, oldVal, newVal) -> resizeMap());

        circleSelectionButton.setOnAction(event -> {
         //   getMapView().getMap().on(MapEvents.MOUSE_MOVE, mapMouseMoveHandler);
            getMapView().getMap().on(MapEvents.CLICK, new CircleDrawAdapter(getMapView()));
        });

        cancelSelectionButton.setOnAction(event -> {
            // отменяет выбор действий, произведенных на карте
            getMapView().getLeaflet().layerGroup().clearLayers();
        });
    }

    @Override
    public void resizeMap() {
        getMapView().setMinSize(getWidth(),getHeight());
        getMapView().setPrefSize(getWidth(),getHeight());

/*        getMapView().setSize((int)getWidth(), (int)getHeight());
        mapPane.getCoordinatesPanel().setBounds(
                (int)getWidth() - mapPane.getCoordinatesPanel().getWidth() - MapPane.BORDER_SIZE,
                (int)getHeight() - mapPane.getCoordinatesPanel().getHeight() - MapPane.BORDER_SIZE,
                mapPane.getCoordinatesPanel().getWidth(), mapPane.getCoordinatesPanel().getHeight()
        );
        mapPane.getBackgroundCoordinatePanel().setBounds(mapPane.getCoordinatesPanel().getBounds());*/
    }

    @Override
    public void updatePanelHeight() {
        // Установка положения панели с элементами выделения
        double y = (getHeight() - (buttonCount * (BUTTON_SIZE + BUTTON_GAP))) / 2;
        AnchorPane.setRightAnchor(selectionBox, (double)BORDER_SIZE);
        AnchorPane.setTopAnchor(selectionBox, y);
    }

    /**
     * Загружает и возвращает иконку из файла с указанным путем и измененным размером size x size
     * @param path путь к файлу с иконкой
     * @param size размер иконки
     * @return возвращает иконку из файла с указанным путем и измененным размером size x size
     */
    public ImageView loadIcon(String path, int size) {
        return loadIcon(path, size, size);
    }

    /**
     * Загружает и возвращает иконку из файла с указанным путем и измененным размером width x height
     * @param path путь к файлу с иконкой
     * @param width ширина возвращаемой иконки
     * @param height высота возвращаемой иконки
     * @return возвращает иконку из файла с указанным путем и измененным размером width x height
     */
    public ImageView loadIcon(String path, int width, int height) {
        InputStream resource = MapPaneImpl.class.getClassLoader().getResourceAsStream(path);
        if (resource == null) return null;

        Image image = new Image(resource);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    /**
     * Отслеживание максимального и минимального зума
     * @param zoomLevel - новый масштаб
     */
/*    @Override
    public void onZoomChange(byte zoomLevel) {
        zoomInButton.setDisable(zoomLevel > mapPane.getMapView().getModel().mapViewPosition.getZoomLevelMax());
        zoomOutButton.setDisable(zoomLevel < mapPane.getMapView().getModel().mapViewPosition.getZoomLevelMin());
    }*/

    /**
     * Устанавливает состояние по-умолчанию для данного контейнера:
     */
/*    public void setDefaultState() {
        mapPane.getMapView().getAdapterManager().clearCurrentAdapters();
        mapPane.getMapView().getModel().mapViewPosition.setCenter(mapPane.getMapView().getMapViewInitializer().getDefaultCenter());
        mapPane.getMapView().getModel().mapViewPosition.setZoomLevel(mapPane.getMapView().getMapViewInitializer().getDefaultZoom());
    }*/

    /**
     * Слушатель на построение фигур (круг, прямоугольник или многоугольник) на карте
     * @param complete если фигура построена, то true
     */
/*
    @Override
    public void onLayerCompleteChanged(boolean complete) {
           saveSelectionButton.setDisable(!complete);
           repeatSelectionButton.setDisable(!complete);
    }
*/

    /**
     * Отписывает объект от получения изменений, и очищает кэш графики.
     */
/*    public void destroy() {
        mapPane.getRectangleDrawAdapter().removeMapLayerObserver(this);
        mapPane.getCircleDrawAdapter().removeMapLayerObserver(this);
        mapPane.getPolygonDrawAdapter().removeMapLayerObserver(this);
        AwtGraphicFactory.clearResourceMemoryCache();
    }*/

    /**
     * Производит конвертацию модели области выделения в слой карты и добавляет его на карту
     * @param selection модель области выделения
     */
/*    private void loadSelection(Selection selection) {
        // Адаптер, который будет подключаться со слоем
        InvokableMouseAdapter<? extends Layer> mouseAdapter = null;
        // Кнопка, которая будет выбрана
        Button toggleButton = null;

        // Генерация слоя, определение элементов, с которыми будут производиться действия
        switch (selection.getSelectionType()) {
            case RECTANGLE -> {
                Polygon rectangle = new Polygon(DrawTools.POLYGON_FILL_PAINT, DrawTools.getLinePaint(Color.BLACK), AwtGraphicFactory.INSTANCE);
                rectangle.getLatLongs().addAll(selection.getSelectionLatLongs());
                toggleButton = boxSelectionButton;
                mapPane.getRectangleDrawAdapter().loadShape(rectangle);
                mouseAdapter = mapPane.getRectangleDrawAdapter();
            }
            case POLYGON -> {
                Polygon polygon = new Polygon(DrawTools.POLYGON_FILL_PAINT, DrawTools.getLinePaint(Color.BLACK), AwtGraphicFactory.INSTANCE);
                polygon.getLatLongs().addAll(selection.getSelectionLatLongs());
                toggleButton = polygonSelectionButton;
                mapPane.getPolygonDrawAdapter().loadShape(polygon);
                mouseAdapter = mapPane.getPolygonDrawAdapter();
            }
            case CIRCLE -> {
                List<LatLong> latLongs = selection.getSelectionLatLongs();
                double radius = selection.getRadius().orElse(0);
                Circle circle = new Circle(latLongs.get(0), (float) radius, DrawTools.POLYGON_FILL_PAINT, DrawTools.getLinePaint(Color.BLACK));
                toggleButton = circleSelectionButton;
                mapPane.getCircleDrawAdapter().loadShape(circle);
                mouseAdapter = mapPane.getCircleDrawAdapter();
            }
        }

        // Если кнопка для построения области выделения с типом, такого же как и для загружаемой модели
        // не выделена - просто выделяем ее, слушатель кнопки автоматически вызовет обработчик с загруженной областью на карту
        if (!toggleButton.isFocused()) {
            toggleButton.requestFocus();
            toggleButton.fire();
        } else {
            // Самостоятельно вызываем адаптер, если он еще не вызван в данный момент
            if (!mapPane.getMapView().getAdapterManager().isInvoked(mouseAdapter)) {
                mapPane.getMapView().getAdapterManager().invokeAdapter(mouseAdapter, true);
            }
        }
    }*/

    /**
     * Getter для centerButton
     * @return возвращает кнопку центровки карты
     */
/*    public Button getCenterButton() {
        return centerButton;
    }*/

    /**
     * Getter для zoomInButton
     * @return возвращает кнопку увеличения масштаба
     */
/*    public Button getZoomInButton() {
        return zoomInButton;
    }*/

    /**
     * Getter для zoomOutButton
     * @return возвращает кнопку уменьшения масштаба
     */
/*    public Button getZoomOutButton() {
        return zoomOutButton;
    }*/

    /**
     * @return Возвращает кнопку построения области выделения внутри прямоугольника
     */
    public Button getBoxSelectionButton() {
        return boxSelectionButton;
    }

    /**
     * @return Возвращает кнопку построения области выделения внутри круга
     */
    public Button getCircleSelectionButton() {
        return circleSelectionButton;
    }

    /**
     * @return Возвращает кнопку построения области выделения внутри многоугольника
     */
    public Button getPolygonSelectionButton() {
        return polygonSelectionButton;
    }

    /**
     * @return Возвращает кнопку вызова инструмента "Линейка"
     */
    public Button getRulerToggleButton() {
        return rulerToggleButton;
    }

    /**
     * @return Возвращает кнопку вызова инструмента "Поиск маршрута"
     */
    public Button getRouteToggleButton() {
        return routeToggleButton;
    }

    /**
     * @return Возвращает кнопку завершения работы с инструментами карты
     */
    public Button getCancelSelectionButton() {
        return cancelSelectionButton;
    }

    /**
     * @return Возвращает кнопку повторного выделения маркеров в построенной области
     */
    public Button getRepeatSelectionButton() {
        return repeatSelectionButton;
    }

    /**
     * @return Возвращает кнопку загрузки области выделения из файла
     */
    public Button getLoadSelectionButton() {
        return loadSelectionButton;
    }

    /**
     * @return Возвращает кнопку сохранения области выделения из файла
     */
    public Button getSaveSelectionButton() {
        return saveSelectionButton;
    }

    /**
     * @return Возвращает файловый менеджер
     */
/*    public FileChooser getFileChooser() {
        return fileChooser;
    }*/

    /**
     * @return Возвращает элемент управления тайловыми подложками
     */
    public ComboBox<Object> getTileSourceComboBox() {
        return tileSourceComboBox;
    }

    /**
     * @return Возвращает слой карты LeafletMapView
     */
    public LeafletMapView getMapView() {
        return mapView;
    }
}
