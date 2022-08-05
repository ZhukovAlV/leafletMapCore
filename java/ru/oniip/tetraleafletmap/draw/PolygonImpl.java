package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.gui.JSContext;

public class PolygonImpl extends PolylineImpl implements Polygon {

  public PolygonImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }
}
