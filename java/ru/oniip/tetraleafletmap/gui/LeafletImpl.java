package ru.oniip.tetraleafletmap.gui;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.layer.LayerGroup;
import ru.oniip.tetraleafletmap.layer.LayerGroupImpl;
import ru.oniip.tetraleafletmap.layer.TileLayer;
import ru.oniip.tetraleafletmap.layer.TileLayerImpl;
import ru.oniip.tetraleafletmap.option.MapOptions;
import ru.oniip.tetraleafletmap.option.TileLayerOptions;

public class LeafletImpl extends JSAdapter implements Leaflet {

  public LeafletImpl(JSContext jsContext) {
    super(jsContext, (JSObject) jsContext.getEngine().executeScript("L"));
  }

  @Override
  public LeafletMap map(String id) {
    final JSObject o = (JSObject) call("map", id);
    return new LeafletMapImpl(getJsContext(), o);
  }

  @Override
  public LeafletMap map(String id, MapOptions mapOptions) {
    final JSObject o = (JSObject) call("map", id, mapOptions);
    return new LeafletMapImpl(getJsContext(), o);
  }

  @Override
  public TileLayer tileLayer(String url) {
    final JSObject o = (JSObject) call("tileLayer", url);
    return new TileLayerImpl(getJsContext(), o);
  }

  @Override
  public TileLayer tileLayer(String url, TileLayerOptions tileLayerOptions) {
    final JSObject o = (JSObject) call("tileLayer", url, tileLayerOptions);
    return new TileLayerImpl(getJsContext(), o);
  }

  @Override
  public LayerGroup layerGroup() {
    final JSObject o = (JSObject) call("layerGroup");
    return new LayerGroupImpl(getJsContext(), o);
  }
}
