import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mazer.Animator;
import mazer.Maze;
import mazer.Player;
import mazer.Utils;
import mazer.Move;
// TODO : I need to find a way to update the playerMapIndexes.
// ATM, the detection of forbidden ouvement only work on the PLAYER first location
public class App extends Application {
    protected final int WIDTH = Maze.mapWidthNumber * 50;
    protected final int HEIGHT = Maze.mapHeightNumber * 50;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting up
        StackPane root = new StackPane();
        Scene mainScene = new Scene(root, WIDTH, HEIGHT);
        mainScene.setFill(Color.GREY);

        // Building the maze
        Maze maze = new Maze();
        maze.setMazeInContainer(Maze.container, root);

        // Handle players Actions
        Player player = new Player();
        Animator animator = new Animator();
        animator.player = player;
        player.setPlayerToDefaultLocalisationInMaze(Maze.container);
        Move.handlePlayerMove(mainScene, player, maze);

        // Ending the setting and show the application
        Utils.setupWindowSettings(primaryStage, "Mazer Game", mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
