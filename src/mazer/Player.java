package mazer;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    public final int radius = 20;
    public final Color COLOR = Maze.RED;
    public final Circle CIRCLE = new Circle(radius);

    public double xPos;
    public double yPos;
    double rowIndex;
    double tileIndex;
    public double speed = Maze.WIDTH;

    public Label position;
    public String labelText;

    public Player() {
        CIRCLE.setFill(COLOR);
    }

    public void setPlayerToDefaultLocalisationInMaze(Pane mazeContainer) {
        int[] playerMapIndexes = Maze.getInitialPlayerLocationIndexes();
        this.rowIndex = playerMapIndexes[0];
        this.tileIndex = playerMapIndexes[1];
        this.xPos = this.tileIndex * 50;
        this.yPos = this.rowIndex * 50;
        updatePlayerInMaze(mazeContainer);
    }

    public int[] extractPlayerIndexesFromPosition(){
        int[] response = new int[2];
        response[0] = (int)this.xPos / 50;
        response[1] = (int)this.yPos / 50;
        return response;
    }

    public void printIndexes(){
        System.out.println(String.format("rowIndex: %s - tileIndex: %s", this.rowIndex, this.tileIndex));
    }

    public void updatePlayerInMaze(Pane mazeContainer){
        mazeContainer.getChildren().remove(this.CIRCLE);
        this.setCirclePosition();
        this.setIndexPosition();
        mazeContainer.getChildren().add(this.CIRCLE);
    }

    public void setCirclePosition(){
        int gap = Maze.WIDTH / 2;
        this.CIRCLE.setLayoutX(this.xPos + gap);
        this.CIRCLE.setLayoutY(this.yPos + gap);
    }

    public void setIndexPosition(){
        this.rowIndex = this.yPos / Maze.HEIGHT;
        this.tileIndex = this.xPos / Maze.WIDTH;
    }

}
