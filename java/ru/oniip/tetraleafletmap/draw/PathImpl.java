package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.gui.JSContext;
import ru.oniip.tetraleafletmap.layer.InteractiveLayerImpl;
import ru.oniip.tetraleafletmap.option.PathOptions;

public class PathImpl extends InteractiveLayerImpl implements Path {

  public PathImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void redraw() {
    call("redraw");
  }

  @Override
  public void setStyle(PathOptions pathOptions) {
    call("setStyle", pathOptions);
  }

  @Override
  public void bringToFront() {
    call("bringToFront");
  }

  @Override
  public void bringToBack() {
    call("bringToBack");
  }
}
