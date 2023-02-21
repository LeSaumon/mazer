package mazer;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utils {
    public static void setupWindowSettings(Stage primaryStage, String title, Scene mainScene){
        primaryStage.setTitle(title);
        primaryStage.setScene(mainScene);
    }
}
