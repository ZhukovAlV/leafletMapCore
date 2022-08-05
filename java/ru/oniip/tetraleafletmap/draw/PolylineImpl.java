package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.gui.JSContext;

import java.util.List;

public class PolylineImpl extends PathImpl implements Polyline {

  public PolylineImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void setLatLngs(List<LatLng> latLngs) {
    call("setLatLngs", latLngs);
  }

  @Override
  public void addLatLng(LatLng latLng) {
    call("addLatLng", latLng);
  }
}
