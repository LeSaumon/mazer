package mazer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze {
    public static final Color BLACK = Color.rgb(0, 18, 25);
    public static final Color PRIMARY_BLUE = Color.rgb(0, 95, 115);
    public static final Color SECONDARY_BLUE = Color.rgb(10, 147, 150);
    public static final Color LIGHT_BLUE = Color.rgb(148, 210, 189);
    public static final Color PALE_YELLOW = Color.rgb(233, 216, 166);
    public static final Color ORANGE = Color.rgb(238, 155, 0);
    public static final Color DARK_ORANGE = Color.rgb(202, 103, 2);
    public static final Color DARKER_ORANGE = Color.rgb(202, 103, 2);
    public static final Color LIGHT_RED = Color.rgb(187, 62, 3);
    public static final Color RED = Color.rgb(174, 32, 18);
    public static final Color DARK_RED = Color.rgb(155, 34, 38);

    public static final int WALLS = 0;
    public static final int FLOORS = 1;
    public static final int PLAYER = 2;
    public static final int TARGET = 3;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public static final int[][] SKELETON_MAZE_MAP = {
            { WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS },
            { PLAYER, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, WALLS, WALLS, FLOORS, WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, WALLS, WALLS, FLOORS, WALLS, FLOORS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, FLOORS, WALLS },
            { WALLS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, WALLS, FLOORS, FLOORS, FLOORS, WALLS },
            { WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, FLOORS, WALLS, WALLS, WALLS },
            { WALLS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, FLOORS, TARGET },
            { WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, WALLS, FLOORS, WALLS, WALLS, WALLS },
    };

    public static Rectangle[][] buildedMazeMap = {
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
            { new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT), new Rectangle(WIDTH,HEIGHT) },
    };

    public int[][] getMazeMap() {
        return SKELETON_MAZE_MAP;
    }

    public static void setRectangleFromSkeleton(Color color, int[] row, int rowIndex, int tileIndex) {
        System.out.println(buildedMazeMap[row[rowIndex]][tileIndex]);
        buildedMazeMap[row[rowIndex]][tileIndex].setFill(color);
        buildedMazeMap[row[rowIndex]][tileIndex].setWidth(WIDTH);
        buildedMazeMap[row[rowIndex]][tileIndex].setHeight(HEIGHT);
        if (tileIndex == 0) {
            buildedMazeMap[row[rowIndex]][tileIndex].setX(0);
        } else {
            buildedMazeMap[row[rowIndex]][tileIndex]
                    .setX(buildedMazeMap[row[rowIndex]][tileIndex - 1].getX() + WIDTH);
        }
        if (rowIndex == 0){
            buildedMazeMap[row[rowIndex]][tileIndex].setY(0);
        } else {
            buildedMazeMap[row[rowIndex]][tileIndex]
            .setY(buildedMazeMap[row[rowIndex]][tileIndex].getY() + HEIGHT);
        }
        buildedMazeMap[row[rowIndex]][tileIndex].setY(HEIGHT);
    }

    public static Rectangle[][] buildMazeMap() {
        int rowIndex = 0;
        int tileIndex = 0;
        for (int[] row : SKELETON_MAZE_MAP) {
            for (int tile : row) {
                if (tileIndex == 11) {
                    tileIndex = 0;
                }
                switch (tile) {
                    case WALLS:
                        setRectangleFromSkeleton(BLACK, row, rowIndex, tileIndex);
                        break;
                    case FLOORS:
                        setRectangleFromSkeleton(BLACK, row, rowIndex, tileIndex);
                    case PLAYER:
                        setRectangleFromSkeleton(BLACK, row, rowIndex, tileIndex);
                    default:
                        // TARGET
                        setRectangleFromSkeleton(BLACK, row, rowIndex, tileIndex);
                        break;
                }
                tileIndex += 1;
            }
            rowIndex += 1;
        }
        return buildedMazeMap;
    }
}
