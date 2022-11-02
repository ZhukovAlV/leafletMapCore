package tetramap.event;

import java.util.ArrayList;

public class DrawShapeEndEventManager {
    private final ArrayList<DrawShapeEndEventListener> listeners = new ArrayList<>();

    public void addListener(DrawShapeEndEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void removeListener(DrawShapeEndEventListener toRemove) {
        listeners.remove(toRemove);
    }

    public void drawEnd(String shape) {
        for (DrawShapeEndEventListener drawShapeEndEventListener : listeners) {
            drawShapeEndEventListener.drawEnd(shape);
        }
    }
}