import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import mazer.Maze;
import mazer.Player;
import mazer.Utils;

public class App extends Application {
    protected final int WIDTH = 550;
    protected final int HEIGHT = 550;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting up
        StackPane root = new StackPane();
        Pane mazeContainer = new Pane();
        Scene mainScene = new Scene(root, WIDTH, HEIGHT);
        mainScene.setFill(Color.GREY);

        // Building the maze
        Maze maze = new Maze();
        maze.setMazeInContainer(mazeContainer, root);
        Rectangle playerLocation = maze.getPlayerPosition();


        // Creating the Player
        Player player = new Player("Michel");
        player.setPlayerInMaze(playerLocation, mazeContainer);

        // Ending the setting and show the application
        Utils.setupWindowSettings(primaryStage, "Mazer Game", mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
