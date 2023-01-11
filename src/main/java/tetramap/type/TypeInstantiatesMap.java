package tetramap.type;

public enum TypeInstantiatesMap {
    MAP("map"), // Карта
    MARKER("marker"), // Маркер
    ICON("icon"), // Иконка (для маркера)
    TILE_LAYER("tileLayer"), // Тайловый слой
    LAYERS("layers"), // Тайловые слои
    BASE_MAPS("baseMaps"), // Набор карт
    ATTRIBUTION_CONTROL("attributionControl"), // Отвечает за префикс (название слоя карты)
    CONTROL("control"), // Control
    SCALE("scale"), // Масштаб
    ZOOM("zoom"), // Зумм
    POLYLINE("polyline"), // Линия
    POLYGON("polygon"), //Полигон
    CIRCLE_MARKER("circleMarker"), // Простая версия круга
    CIRCLE("circle"), // Круг расширенный (наследуется от circleMarker)
    RECTANGLE("rectangle"), // Квадрат
    LAYER_GROUP("layerGroup"), // Слой группировки других слоев
    FEATURE_GROUP("featureGroup"), // Унаследованный от layerGroup слой группировки других слоев c доп. методами
    MARKER_CLUSTER_GROUP("markerClusterGroup"), // Кластер для маркеров
    DIV_OVERLAY("divOverlay"), // DivOverlay
    POPUP("popup"), // Popup (всплывающее окошко)
    POLYLINE_DECORATOR("polylineDecorator"), // L.polylineDecorator - декоратор для оформления маршрута
    SYMBOL_DECORATOR("Symbol"); // Вариант оформления polylineDecorator

    private final String name;

    TypeInstantiatesMap(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
