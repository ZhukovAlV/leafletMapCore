package tetramap.type;

public enum TypeInstantiatesMap {
    map, // Карта
    marker, // Маркер
    icon, // Иконка (для маркера)
    tileLayer, // Тайловый слой
    layers, // Тайловые слои
    baseMaps, // Набор карт
    attributionControl, // Отвечает за префикс (название слоя карты)
    control, // Control
    scale, // Масштаб
    zoom, // Зумм
    polyline, // Линия
    polygon, //Полигон
    circleMarker, // Простая версия круга
    circle, // Круг расширенный (наследуется от circleMarker)
    rectangle, // Квадрат
    layerGroup, // Слой группировки других слоев
    markerClusterGroup, // Кластер для маркеров
    divOverlay, // DivOverlay
    popup // Popup (всплывающее окошко)
}
