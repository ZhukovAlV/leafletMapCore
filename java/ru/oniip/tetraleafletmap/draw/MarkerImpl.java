package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.gui.JSContext;
import ru.oniip.tetraleafletmap.layer.LayerImpl;

public class MarkerImpl extends LayerImpl implements Marker {

  public MarkerImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void setLatLng(LatLng latLng) {
    call("setLatLng", latLng);
  }

  @Override
  public void setZIndexOffset(Number offset) {
    call("setZIndexOffset", offset);
  }

  @Override
  public void setIcon(Icon icon) {
    call("setIcon", icon);
  }

  @Override
  public void setOpacity(Number opacity) {
    call("setOpacity", opacity);
  }
}
