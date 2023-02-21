import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import mazer.Utils;
import mazer.Maze;
import mazer.Player;

public class App extends Application {
    protected final int WIDTH = 800;
    protected final int HEIGHT = 600;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting up
        StackPane root = new StackPane();
        Pane mazeContainer = new Pane();
        Scene mainScene = new Scene(root, WIDTH, HEIGHT);

        // Building the maze
        Maze maze = new Maze();
        maze.setMazeInContainer(mazeContainer, root);

        // TODO : Integrate the player in game
        Player player = new Player("Michel");
        player.getCircle();

        // Ending the setting and show the application
        Utils.setupWindowSettings(primaryStage, "Mazer Game", mainScene);
        primaryStage.show();
    }

}
