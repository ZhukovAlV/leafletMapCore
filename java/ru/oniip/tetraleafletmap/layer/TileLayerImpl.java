package ru.oniip.tetraleafletmap.layer;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.gui.JSContext;

public class TileLayerImpl extends GridLayerImpl implements TileLayer {

  public TileLayerImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void setUrl(String url) {
    call("setUrl", url);
  }

  @Override
  public void setUrl(String url, boolean noRedraw) {
    call("setUrl", url, noRedraw);
  }
}
