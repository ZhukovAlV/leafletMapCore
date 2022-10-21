package tetramap.entity.popup;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import tetramap.entity.DivOverlay;
import tetramap.entity.types.Point;
import tetramap.type.TypeInstantiatesMap;

import java.io.Serial;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Popup extends DivOverlay {

    @Serial
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
    }

    @Override
    public String toString() {
        return "({" +
                "maxWidth:" + maxWidth +
                ", minWidth:" + minWidth +
                ", maxHeight:" + maxHeight +
                ", autoPan:" + autoPan +
                ", autoPanPaddingTopLeft:" + autoPanPaddingTopLeft +
                ", autoPanPaddingBottomRight:" + autoPanPaddingBottomRight +
                ", autoPanPadding:" + autoPanPadding +
                ", keepInView:" + keepInView +
                ", closeButton:" + closeButton +
                ", autoClose:" + autoClose +
                ", closeOnEscapeKey:" + closeOnEscapeKey +
                ", closeOnClick:" + closeOnClick +
                "})";
    }

    @Override
    public String getTypeInstantiatesMap() {
        return TypeInstantiatesMap.popup.toString();
    }
}
