package tetramap.entity.popup;

import tetramap.entity.DivOverlay;
import tetramap.entity.types.Point;

public class Popup extends DivOverlay {

    private static final long serialVersionUID = 4544720725678791374L;
    private Integer maxWidth = 300;
    private Integer minWidth = 50;
    private Integer maxHeight;
    private boolean autoPan = true;
    private Point autoPanPaddingTopLeft;
    private Point autoPanPaddingBottomRight;
    private Point autoPanPadding = Point.of(5, 5);
    private boolean keepInView;
    private boolean closeButton = true;
    private boolean autoClose = true;
    private boolean closeOnEscapeKey = true;
    private boolean closeOnClick = true;

    public Popup(String content) {
        super(content);
        setPane("popupPane");
    }

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Integer getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Integer minWidth) {
        this.minWidth = minWidth;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public boolean isAutoPan() {
        return autoPan;
    }

    public void setAutoPan(boolean autoPan) {
        this.autoPan = autoPan;
    }

    public Point getAutoPanPaddingTopLeft() {
        return autoPanPaddingTopLeft;
    }

    public void setAutoPanPaddingTopLeft(Point autoPanPaddingTopLeft) {
        this.autoPanPaddingTopLeft = autoPanPaddingTopLeft;
    }

    public Point getAutoPanPaddingBottomRight() {
        return autoPanPaddingBottomRight;
    }

    public void setAutoPanPaddingBottomRight(Point autoPanPaddingBottomRight) {
        this.autoPanPaddingBottomRight = autoPanPaddingBottomRight;
    }

    public Point getAutoPanPadding() {
        return autoPanPadding;
    }

    public void setAutoPanPadding(Point autoPanPadding) {
        this.autoPanPadding = autoPanPadding;
    }

    public boolean isKeepInView() {
        return keepInView;
    }

    public void setKeepInView(boolean keepInView) {
        this.keepInView = keepInView;
    }

    public boolean isCloseButton() {
        return closeButton;
    }

    public void setCloseButton(boolean closeButton) {
        this.closeButton = closeButton;
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }

    public boolean isCloseOnEscapeKey() {
        return closeOnEscapeKey;
    }

    public void setCloseOnEscapeKey(boolean closeOnEscapeKey) {
        this.closeOnEscapeKey = closeOnEscapeKey;
    }

    public boolean isCloseOnClick() {
        return closeOnClick;
    }

    public void setCloseOnClick(boolean closeOnClick) {
        this.closeOnClick = closeOnClick;
    }
}
