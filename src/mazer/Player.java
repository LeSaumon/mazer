package mazer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private final int radius = 20;
    String name;
    Color color = Maze.RED;
    Circle circle = new Circle(radius);

    public Player(String name){
        this.name = name;
    }

    public Circle getCircle(){
        return circle;
    }

}
