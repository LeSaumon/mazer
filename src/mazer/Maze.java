package mazer;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze {
    public static final Color DARK_BLUE = Color.rgb(24, 78, 119);
    public static final Color BLUE = Color.rgb(30, 96, 145);
    public static final Color LIGHT_BLUE = Color.rgb(26, 117, 159);
    public static final Color LIGHTER_BLUE = Color.rgb(22, 138, 173);
    public static final Color RED = Color.rgb(217, 4, 41);
    public static final Pane container = new Pane();

    public static final int WALLS = 0;
    public static final int FLOORS = 1;
    public static final int PLAYER = 2;
    public static final int TARGET = 3;
    public static final int STOP = 4;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public static final int mapWidthNumber = 13;
    public static final int mapHeightNumber = 13;
    public static Label label = new Label("");

    public static final int[][] SKELETON_MAZE_MAP = {
            { WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS },
            { PLAYER, STOP, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, WALLS, WALLS, WALLS, WALLS, FLOORS, WALLS, FLOORS, WALLS, WALLS, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, STOP, FLOORS, FLOORS, FLOORS, STOP, FLOORS, FLOORS, FLOORS, STOP, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS, FLOORS, WALLS, FLOORS, WALLS, WALLS, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, FLOORS, STOP, FLOORS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, FLOORS },
            { WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS }
    };

    public static Rectangle[][] builtMazeMap = {
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
            { new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT), new Rectangle(WIDTH, HEIGHT),
                    new Rectangle(WIDTH, HEIGHT) },
    };

    public Maze() {
        buildMaze();
    }

    public void setMazeInContainer(Pane mazeContainer, StackPane masterContainer) {
        for (Rectangle[] row : builtMazeMap) {
            mazeContainer.getChildren().addAll(row);
        }
        masterContainer.getChildren().add(mazeContainer);
    }

    public int[] getTargetPosition() {
        int[] targetLocation = new int[] {};
        lookout: for (int rowIndex = 0; rowIndex < SKELETON_MAZE_MAP.length; rowIndex++) {
            for (int tileIndex = 0; tileIndex < SKELETON_MAZE_MAP[rowIndex].length; tileIndex++) {
                if (SKELETON_MAZE_MAP[rowIndex][tileIndex] == TARGET) {
                    targetLocation[0] = rowIndex;
                    targetLocation[1] = tileIndex;
                    break lookout;
                }
            }
        }
        return targetLocation;
    }

    public static int[] getInitialPlayerLocationIndexes() {
        int[] targetLocation = new int[2];
        lookout: for (int rowIndex = 0; rowIndex < SKELETON_MAZE_MAP.length; rowIndex++) {
            for (int tileIndex = 0; tileIndex < SKELETON_MAZE_MAP[rowIndex].length; tileIndex++) {
                if (SKELETON_MAZE_MAP[rowIndex][tileIndex] == PLAYER) {
                    targetLocation[0] = rowIndex;
                    targetLocation[1] = tileIndex;
                    break lookout;
                }
            }
        }
        return targetLocation;
    }

    public static void setRectangleFromSkeleton(Color color, int rowIndex, int tileIndex) {
        Rectangle currentTile = builtMazeMap[rowIndex][tileIndex];
        if (rowIndex != 0) {
            currentTile.setY(builtMazeMap[rowIndex - 1][tileIndex].getY() + HEIGHT);
        } else {
            currentTile.setY(0);
        }
        if (tileIndex == 0) {
            currentTile.setX(0);
        } else {
            Rectangle previousTile = builtMazeMap[rowIndex][tileIndex - 1];
            currentTile.setX(previousTile.getX() + WIDTH);
        }
        currentTile.setFill(color);
    }

    public static Rectangle[][] buildMaze() {
        for (int rowIndex = 0; rowIndex < SKELETON_MAZE_MAP.length; rowIndex++) {
            for (int tileIndex = 0; tileIndex < SKELETON_MAZE_MAP[rowIndex].length; tileIndex++) {
                switch (SKELETON_MAZE_MAP[rowIndex][tileIndex]) {
                    case WALLS:
                        setRectangleFromSkeleton(DARK_BLUE, rowIndex, tileIndex);
                        break;
                    case FLOORS:
                        setRectangleFromSkeleton(LIGHT_BLUE, rowIndex, tileIndex);
                        break;
                    case PLAYER:
                        setRectangleFromSkeleton(LIGHTER_BLUE, rowIndex, tileIndex);
                        break;
                    case TARGET:
                        setRectangleFromSkeleton(LIGHTER_BLUE, rowIndex, tileIndex);
                        break;
                    default:
                        setRectangleFromSkeleton(LIGHT_BLUE, rowIndex, tileIndex);
                        break;
                }
            }
        }
        return builtMazeMap;
    }

    public static void updateLabelInContainer(Pane mazeContainer, Player player) {
        mazeContainer.getChildren().remove(Maze.label);
        Maze.label.setText("Nombres de coups : " + player.playCount);
        mazeContainer.getChildren().add(Maze.label);
    }

}
