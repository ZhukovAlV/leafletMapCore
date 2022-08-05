package ru.oniip.tetraleafletmap.layer;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.gui.JSContext;

public class InteractiveLayerImpl extends LayerImpl implements InteractiveLayer {

  public InteractiveLayerImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }
}
