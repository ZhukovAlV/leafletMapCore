package tetramap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tetramap.gui.MapPaneFX;

public class MainFXApp extends Application {

    private final int WIGHT = 1000, HIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        MapPaneFX root = new MapPaneFX();
        root.initialize();

        Scene scene = new Scene(root, WIGHT, HIGHT);
        primaryStage.setScene(scene);

        primaryStage.setTitle("TetraMap in JavaFX");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

