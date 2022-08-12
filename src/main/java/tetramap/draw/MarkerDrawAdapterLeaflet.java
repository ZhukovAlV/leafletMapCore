package tetramap.draw;

import tetramap.entity.Icon;
import tetramap.entity.IconLeaflet;
import tetramap.entity.LatLong;
import tetramap.gui.MapViewLeaflet;

public class MarkerDrawAdapterLeaflet implements MarkerDrawAdapter {

    // Карта
    private final MapViewLeaflet mapView;

    public MarkerDrawAdapterLeaflet(MapViewLeaflet mapView) {
        this.mapView = mapView;
    }

    @Override
    public void mouseClicked(LatLong latLong) {
        Icon icon = new IconLeaflet(getClass().getResource("../../markerIcon/subscriber/marker_green.png").getPath());
        mapView.execScript("var myIcon = L.icon({iconUrl: '" + icon.getIconUrl() + "', iconSize: [24, 24], iconAnchor: [12, 12], });");

       // mapView.execScript("L.marker([" + latLong.getLatitude() + "," + latLong.getLongitude() + "]).addTo(map);");
        mapView.execScript("L.marker([" + latLong.getLatitude() + "," + latLong.getLongitude() + "], {icon: myIcon}).addTo(map);");
    }

    @Override
    public void clear() {
      //  mapView.execScript("marker.remove()");
    }

    @Override
    public void draw() {
        mapView.addMouseClickListener(this);
    }
}
