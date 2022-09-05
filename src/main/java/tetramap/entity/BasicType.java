package tetramap.entity;

import java.io.Serializable;

public interface BasicType extends Serializable {

    default String getLeafletType() {
        return getClass().getSimpleName();
    }
}