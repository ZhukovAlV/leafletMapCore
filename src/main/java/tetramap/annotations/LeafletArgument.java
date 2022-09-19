package tetramap.annotations;

import java.lang.annotation.*;

/**
 * Most Leaflet classes have constructor with custom arguments. The fields in
 * the @Layer classes marked with this annotation will be injected as
 * constructor arguments on client-side during the layer instantiation. You can
 * change the argument orders with index parameter.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface LeafletArgument {
    int index() default 0;
}