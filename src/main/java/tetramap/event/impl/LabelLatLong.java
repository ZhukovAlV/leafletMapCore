package tetramap.event.impl;

import javafx.scene.control.Label;
import tetramap.entity.LatLong;
import tetramap.event.MapMoveEventListener;

public class LabelLatLong extends Label implements MapMoveEventListener {
    @Override
    public void mouseMoved(LatLong latLong) {
        setText(latLong.getLatitude() + " " + latLong.getLongitude());
    }
}