import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mazer.Utils;
import mazer.Maze;

public class App extends Application {
    protected final int WIDTH = 800;
    protected final int HEIGHT = 600;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting up
        Pane root = new Pane();
        Scene mainScene = new Scene(root, WIDTH, HEIGHT);

        Rectangle[][] maze = Maze.buildMazeMap();
        for (Rectangle[] row : maze) {
            root.getChildren().addAll(row);
        }
        // System.out.println(Arrays.deepToString(maze));
        Utils.setupWindowSettings(primaryStage, "Mazer Game", mainScene);
        primaryStage.show();
    }

}
