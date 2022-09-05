package tetramap.layer;

import java.io.Serial;

public abstract class InteractiveLayer extends Layer {

    @Serial
    private static final long serialVersionUID = -8240959432268467723L;

    private boolean interactive = true;
    private boolean bubblingMouseEvents = true;

    protected InteractiveLayer() {
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(final boolean interactive) {
        this.interactive = interactive;
    }

    public boolean isBubblingMouseEvents() {
        return bubblingMouseEvents;
    }

    public void setBubblingMouseEvents(final boolean bubblingMouseEvents) {
        this.bubblingMouseEvents = bubblingMouseEvents;
    }
}