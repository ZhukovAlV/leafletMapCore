package tetramap.type;

public enum MapLayerType {
    /** OpenStreetMap layer */
    OPENSTREETMAP("OpenStreetMap", """
        L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; OpenStreetMap and contributors',
        })"""),

    /** OpenStreetMap layer LOCAL*/
    OPENSTREETMAP_LOCAL("OpenStreetMap", """
        L.tileLayer('/home/user/Test/leafletLocal/moscow/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; OpenStreetMap and contributors',
        })"""),

    /** OpenCycleMap layer */
    OPENCYCLEMAP("OpenCycleMap", """
        L.tileLayer('http://{s}.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenCycleMap, Map data &copy; OpenStreetMap contributors',
        })"""),

    /** YandexMap layer */
    YANDEXMAP("YandexMap", """
        L.tileLayer('http://sat0{s}.maps.yandex.net/tiles?l=sat&x={x}&y={y}&z={z}', {
            attribution: 'Yandex map',
            isElliptical: true,
            subdomains: "1234",
            minZoom: 0,
            maxZoom: 18,
        })"""),

    /** GIS map layer */
    GISMAP("2GIS", """
        L.tileLayer('http://tile{s}.maps.2gis.com/tiles?x={x}&y={y}&z={z}', {
            attribution: '2GIS map',
            isElliptical: false,
            subdomains: "0123",
            minZoom: 0,
            maxZoom: 18,
        })"""),

    /** Google map layer */
    GOOGLEMAP("Google map", """
        L.tileLayer('http://mts{s}.google.com/vt/hl=ru&x={x}&y={y}&z={z}', {
            attribution: 'Google map',
            isElliptical: false,
            subdomains: "0123",
            minZoom: 0,
            maxZoom: 18,
        })"""),

    /** World street map layer */
    WORLDSTREETMAP("World street map", """
        L.tileLayer('http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}', {
            attribution: '2GIS map',
            isElliptical: false,
            subdomains: "abc",
            minZoom: 0,
            maxZoom: 18,
        })""");

    private final String displayName;
    private final String javaScriptCode;

    MapLayerType(String displayName, String javaScriptCode) {
        this.displayName = displayName;
        this.javaScriptCode = javaScriptCode;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getJavaScriptCode() {
        return javaScriptCode;
    }
}
