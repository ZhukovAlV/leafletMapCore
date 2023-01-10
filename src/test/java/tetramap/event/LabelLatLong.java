package tetramap.event;

import javafx.scene.control.Label;
import tetramap.entity.types.LatLong;

public class LabelLatLong extends Label implements MapMoveEventListener {
    @Override
    public void mouseMoved(LatLong latLong) {
        setText(latLong.getLatitude() + " " + latLong.getLongitude());
    }
}
