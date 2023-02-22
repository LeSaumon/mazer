package mazer;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Player {
    private final int radius = 20;
    String name;
    Color color = Maze.RED;
    Circle circle = new Circle(radius);

    public Player(String name){
        this.name = name;
        circle.setFill(color);
    }

    public Circle getCircle(){
        return circle;
    }

    public void setPlayerInMaze(Rectangle playerLocation, Pane container){
        this.circle.setLayoutX(playerLocation.getX() + Maze.WIDTH / 2);
        this.circle.setLayoutY(playerLocation.getY() + Maze.HEIGHT / 2);
        container.getChildren().add(this.circle);
    }

}
