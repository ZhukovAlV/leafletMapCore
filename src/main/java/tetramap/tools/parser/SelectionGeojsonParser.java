package tetramap.tools.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.*;
import tetramap.entity.selection.Selection;
import tetramap.entity.types.LatLong;
import tetramap.entity.vectors.structure.LatLongArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Конвертер моделей областей выделения {@link Selection} в geojson файл и обратно
 */
public class SelectionGeojsonParser implements SelectionParser {

    // JSON ключи
    private static final String SELECTION_TYPE_KEY = "selectionType";
    private static final String RADIUS_KEY = "radius";

    @Override
    public void write(File file, Selection selection) {

        FeatureCollection features = new FeatureCollection();

        List<LngLatAlt> coordinates = new ArrayList<>();

        for (LatLong latLong : selection.getSelectionLatLongs()) {

            coordinates.add(new LngLatAlt(latLong.getLongitude(), latLong.getLatitude()));
        }

        Feature feature = new Feature();
        feature.setProperty(SELECTION_TYPE_KEY, selection.getSelectionType().toString());


        if (selection.getSelectionType() != Selection.SelectionType.CIRCLE) {
            feature.setGeometry(new Polygon(coordinates));
        } else {
            feature.setGeometry(new Point(coordinates.get(0)));
            feature.setProperty(RADIUS_KEY, selection.getRadius().orElse(0));
        }

        features.add(feature);

        try {
            new ObjectMapper().writeValue(file, features);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Selection parse(File file) throws Exception {
        LatLongArray latLongs = new LatLongArray();

        FeatureCollection featureCollection = new  ObjectMapper().readValue(file, FeatureCollection.class);

        Feature feature = featureCollection.getFeatures().get(0);
        Selection.SelectionType selectionType = Selection.SelectionType.valueOf(feature.getProperty(SELECTION_TYPE_KEY));

        Selection selection;
        if (selectionType != Selection.SelectionType.CIRCLE) {
            List<LngLatAlt> coordinates = ((Polygon) feature.getGeometry()).getCoordinates().get(0);
            for (LngLatAlt lngLatAlt : coordinates) {
                latLongs.add(new LatLong(lngLatAlt.getLatitude(), lngLatAlt.getLongitude()));
            }
            selection = new Selection(selectionType, latLongs);
        } else {
            LngLatAlt center = ((Point) feature.getGeometry()).getCoordinates();
            selection = new Selection(selectionType, new LatLongArray(List.of(
                    new LatLong(center.getLatitude(), center.getLongitude()))
            ));
            selection.setRadius(OptionalDouble.of(feature.getProperty(RADIUS_KEY)));
        }

        return selection;
    }
}
