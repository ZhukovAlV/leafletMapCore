package tetramap.route;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.Parameters;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;
import lombok.extern.log4j.Log4j2;
import tetramap.entity.types.LatLong;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Менеджер карты для маршрутизации движения между точками на карте
 * Для использования менеджера требуется использование данных OpenStreetMap в формате osm или pbf
 * Источник данных задается параметром osmDataFile в файле конфигурации map.properties
 * Перед началом работы следует инициализировать данные менеджера вызовом метода {@link RouteManager#initializeData(File)}
 * Для проверки готовности данных к обработке используйте метод {@link RouteManager#isDataAvailable()}
 */
@Log4j2
public class RouteManager {

    // Главный объект API построения маршрута движения
    private final GraphHopper graphHopper;
    // Запрос для маршрута по-умолчанию
    private final GHRequest defaultRequest;
    // Тип транспортного средства по-умолчанию
    private static final VehicleType DEFAULT_VEHICLE = VehicleType.CAR;
    // Флаг доступности навигации
    private boolean dataAvailable = false;

    public RouteManager() {
        graphHopper = new GraphHopper();
        defaultRequest = new GHRequest().setAlgorithm(Parameters.Algorithms.DIJKSTRA_BI).setProfile("car");
    }

    /**
     * Загружает пространственные данные (OpenStreetMap), предоставляемые файлом (поддерживаются форматы osm и pbf)
     * При первичной загрузке данных создает файлы с кешированными данными в каталоге с предоставляемым файлом
     * <b>Внимание! Первичная загрузка данных может занять продолжительное время</b>
     * При последующих загрузках выполняется импорт кешированных файлов
     * TODO: Добавить функцию разделения каталогов для разных источников данных (либо очистку старых файлов кеша)
     * @param osmDataFile файл с векторной картой в формате .pbf
     */
    public void initializeData(File osmDataFile) {
        try {
            graphHopper.setOSMFile(osmDataFile.getAbsolutePath());
            graphHopper.setGraphHopperLocation(osmDataFile.getParent());
            graphHopper.setEncodingManager(EncodingManager.create("car"));
            graphHopper.setProfiles((new Profile("car")).setVehicle("car").setWeighting("fastest").setTurnCosts(false));
            graphHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
            graphHopper.importOrLoad();

            dataAvailable = true;
        } catch (Exception e) {
            log.warn("Произошла ошибка при загрузке данных навигации! Функции навигации будут отключены: " + e);
        }
    }

    /**
     * Запрос маршрута движения по набору точек, через которые осуществляется движение
     * Для установки параметров принимается запрос по-умолчанию (тип движения {@link VehicleType#CAR})
     * Возвращает результат поиска с расстоянием и временем движения
     * Точки рассчитанного маршрута могут не проходить точно через исходные, поскольку каждая
     * точка привязывается к ближайшей из дорог
     * @param latLongs набор точек, через которые осуществляется движение
     * @return результат с данными, либо {@link Optional#empty()} в случае неудачного запроса
     * @throws IllegalStateException в случае, если данные не инициализированы
     */
    public Optional<RouteResult> getRouteResultFor(List<LatLong> latLongs) throws IllegalStateException {
        defaultRequest.getPoints().clear();
        if (latLongs == null || latLongs.isEmpty()) return Optional.empty();
        latLongs.forEach(latLong ->
                defaultRequest.addPoint(
                        new GHPoint(latLong.getLatitude(), latLong.getLongitude())
                )
        );
        return getRouteResultFor(defaultRequest, DEFAULT_VEHICLE);
    }

    /**
     * Запрос маршрута движения по дорогам по API запросу и типу траспортного средства
     * Возвращает результат поиска с расстоянием и временем движения
     * @param request запрос для API graphhopper
     * @param vehicleType тип транспорта - в зависимости от типа выбираются допустимые виды дорог для передвижения и скорость движения
     * @return результат с данными, либо {@link Optional#empty()} в случае неудачного запроса
     * @throws IllegalStateException в случае, если данные не инициализированы
     */
    public Optional<RouteResult> getRouteResultFor(GHRequest request, VehicleType vehicleType) throws IllegalStateException {
        if (dataAvailable) {
            List<LatLong> routePoints = new ArrayList<>();
            // Построение маршрута через API
            GHResponse response = graphHopper.route(request);
            try {
                // Получаем лучший вариант - если вариантов не найдено, то будет брошено исключение
                ResponsePath resultPath = response.getBest();
                // Получаем точки для лучшего варианта
                PointList resultList = resultPath.getPoints();
                // Преобразование GraphHopper API в точки Mapsforge API
                for (int i = 0; i < resultList.size(); i++) {
                    routePoints.add(new LatLong(resultList.getLat(i), resultList.getLon(i)));
                }

                return Optional.of(new RouteResult(routePoints, resultPath.getDistance(), resultPath.getTime()));
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            throw new IllegalStateException("Данная функция недоступна, поскольку нет загруженных карт для навигации");
        }
    }

    /**
     * Возвращает новый список точек, построенный graphhopper
     *
     * @param latLongs список latLongs для построения маршрута
     * @return новый список точек, построенный graphhopper
     */
    public List<LatLong> getRouteFor(List<LatLong> latLongs) throws IllegalStateException {

        defaultRequest.getPoints().clear();
        if (latLongs == null || latLongs.isEmpty()) return Collections.emptyList();
        latLongs.forEach(latLong ->
                defaultRequest.addPoint(
                        new GHPoint(latLong.getLatitude(), latLong.getLongitude())
                )
        );
        return getRouteFor(defaultRequest, DEFAULT_VEHICLE);
    }

    /**
     * Запрос маршрута движения по дорогам по API запросу и типу траспортного средства
     * Возвращает набор точек, составляющих маршрут, точки упорядочены в порядке движения
     * @param request запрос для API graphhopper
     * @param vehicleType тип транспорта - в зависимости от типа выбираются допустимые виды дорог для передвижения и скорость движения
     * @return набор точек, составляющих искомый маршрут
     * @throws IllegalStateException в случае, если данные не инициализированы
     */
    public List<LatLong> getRouteFor(GHRequest request, VehicleType vehicleType) throws IllegalStateException {

        Optional<RouteResult> routeResult = getRouteResultFor(request, vehicleType);
        if (routeResult.isPresent()) {
            return routeResult.get().getLatLongs();
        } else {
            return Collections.emptyList();
        }
    }

    public boolean isDataAvailable() {
        return dataAvailable;
    }
}
