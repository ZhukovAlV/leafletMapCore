package ru.oniip.tetraleafletmap.handler;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.gui.JSAdapter;
import ru.oniip.tetraleafletmap.gui.JSContext;

public class HandlerImpl extends JSAdapter implements Handler {

  public HandlerImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void enable() {
    call("enable");
  }

  @Override
  public void disable() {
    call("disable");
  }

  @Override
  public boolean isEnabled() {
    final Object result = call("enabled");
    if (result instanceof Boolean) {
      return (Boolean) result;
    }
    return false;
  }
}
