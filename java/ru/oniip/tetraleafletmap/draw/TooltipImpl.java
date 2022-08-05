package ru.oniip.tetraleafletmap.draw;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.gui.JSContext;
import ru.oniip.tetraleafletmap.layer.LayerImpl;

public class TooltipImpl extends LayerImpl implements Tooltip {

  public TooltipImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }
}
