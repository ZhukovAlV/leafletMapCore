package ru.oniip.tetraleafletmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.oniip.tetraleafletmap.controller.MapController;

import java.io.IOException;

public class MapApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(MapApplication.class.getResource("view/map.fxml"));
        final Parent root = fxmlLoader.load();
        final MapController controller = fxmlLoader.getController();
        stage.setTitle("TetraLeaflet Demo");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}