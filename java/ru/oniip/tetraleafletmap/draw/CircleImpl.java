package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.gui.JSContext;

public class CircleImpl extends PathImpl implements Circle {

  public CircleImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void setCenter(LatLng latLng) {
    call("setLatLng", latLng);
  }

  @Override
  public void setRadius(Number radius) {
    call("setRadius", radius);
  }
}
