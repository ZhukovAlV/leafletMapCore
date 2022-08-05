package ru.oniip.tetraleafletmap.layer;

import netscape.javascript.JSObject;
import ru.oniip.tetraleafletmap.event.AbstractEvented;
import ru.oniip.tetraleafletmap.gui.JSContext;
import ru.oniip.tetraleafletmap.gui.LeafletMap;

public class LayerImpl extends AbstractEvented implements Layer {

  public LayerImpl(JSContext jsContext, JSObject jsObject) {
    super(jsContext, jsObject);
  }

  @Override
  public void addTo(LeafletMap map) {
    call("addTo", map);
  }

  @Override
  public void addTo(LayerGroup layerGroup) {
    call("addTo", layerGroup);
  }

  @Override
  public void remove() {
    call("remove");
  }

  @Override
  public void removeFrom(LeafletMap map) {
    call("removeFrom", map);
  }

  @Override
  public void removeFrom(LayerGroup layerGroup) {
    call("removeFrom", layerGroup);
  }
}
