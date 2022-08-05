package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.entity.LatLng;
import ru.oniip.tetraleafletmap.gui.JSContext;
import ru.oniip.tetraleafletmap.gui.LeafletMap;
import ru.oniip.tetraleafletmap.layer.LayerImpl;

public class PopupImpl extends LayerImpl implements Popup {

  public PopupImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void setLatLng(LatLng latLng) {
    call("setLatLng", latLng);
  }

  @Override
  public void setContent(String content) {
    call("setContent", content);
  }

  @Override
  public void update() {
    call("update");
  }

  @Override
  public boolean isOpen() {
    final Object result = call("isOpen");
    if (result instanceof Boolean) {
      return (Boolean) result;
    }
    return false;
  }

  @Override
  public void bringToFront() {
    call("bringToFront");
  }

  @Override
  public void bringToBack() {
    call("bringToBack");
  }

  @Override
  public void openOn(LeafletMap map) {
    call("openOn", map);
  }
}
