package mazer;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public interface Move {
    public final KeyCode UP = Enum.UP.key;
    public final KeyCode LEFT = Enum.LEFT.key;
    public final KeyCode DOWN = Enum.DOWN.key;
    public final KeyCode RIGHT = Enum.RIGHT.key;

    public static void handlePlayerMove(Scene mainScene, Player player, Maze maze) {

        mainScene.setOnKeyPressed(e -> {
            player.playCount += 1;
            Maze.updateLabelInContainer(Maze.container, player);
            switch (e.getCode()) {
                case UP:
                    Boolean upperWall = detectWallsOrStop(UP, player);
                    if (!upperWall) {
                        movePlayer(player, UP);
                        handleRecursivePlayerMove(mainScene, player, maze, UP);
                    }
                    break;

                case LEFT:
                    Boolean leftWall = detectWallsOrStop(LEFT, player);
                    if (!leftWall) {
                        movePlayer(player, LEFT);
                        handleRecursivePlayerMove(mainScene, player, maze, LEFT);
                    }
                    break;

                case DOWN:
                    Boolean downWall = detectWallsOrStop(DOWN, player);
                    if (!downWall) {
                        movePlayer(player, DOWN);
                        handleRecursivePlayerMove(mainScene, player, maze, DOWN);
                    }
                    break;
                default:
                    Boolean rightWall = detectWallsOrStop(RIGHT, player);
                    if (!rightWall) {
                        movePlayer(player, RIGHT);
                        handleRecursivePlayerMove(mainScene, player, maze, RIGHT);
                    }
                    break;
            }
        });
    }

    public static int getActualTileFromIndexes(int rowIndex, int tileIndex) {
        return Maze.SKELETON_MAZE_MAP[rowIndex][tileIndex];
    }

    public static int getNextTileFromDirection(KeyCode direction, Player player) {
        switch (direction) {
            case UP:
                return Maze.SKELETON_MAZE_MAP[(int) player.rowIndex - 1][(int) player.tileIndex];
            case LEFT:
                return Maze.SKELETON_MAZE_MAP[(int) player.rowIndex][(int) player.tileIndex - 1];
            case DOWN:
                return Maze.SKELETON_MAZE_MAP[(int) player.rowIndex + 1][(int) player.tileIndex];
            default:
                return Maze.SKELETON_MAZE_MAP[(int) player.rowIndex][(int) player.tileIndex + 1];

        }
    }

    public static void handleRecursivePlayerMove(Scene mainScene, Player player, Maze maze, KeyCode direction) {
        Boolean walls = detectWallsOrStop(direction, player);
        if (!walls) {
            movePlayer(player, direction);
            handleRecursivePlayerMove(mainScene, player, maze, direction);
        } else {
            int nextTile = getNextTileFromDirection(direction, player);
            int actualTile = getActualTileFromIndexes((int) player.rowIndex, (int) player.tileIndex);
            // Need to check for a STOP, and Stop it if it is
            if (actualTile == Maze.STOP | actualTile == Maze.PLAYER) {
                handlePlayerMove(mainScene, player, maze);
            } else {
                if (nextTile == Maze.WALLS) {
                    String newDirection = detectNextDirection(mainScene, player, maze, direction);
                    if (newDirection != "NONE") {
                        KeyCode key = KeyCode.getKeyCode(newDirection);
                        handleRecursivePlayerMove(mainScene, player, maze, key);
                    } else {
                        handlePlayerMove(mainScene, player, maze);
                    }
                }
            }
        }
    }

    public static String detectNextDirection(Scene mainScene, Player player, Maze maze, KeyCode direction) {
        switch (direction) {
            case UP:
                return checkAdjacentsTiles(UP, player);
            case LEFT:
                return checkAdjacentsTiles(LEFT, player);
            case DOWN:
                return checkAdjacentsTiles(DOWN, player);
            default:
                return checkAdjacentsTiles(RIGHT, player);
        }
    }

    public static String checkAdjacentsTiles(KeyCode direction, Player player) {
        String returnedDirection = "NONE";
        HashMap<KeyCode, Integer> tileDict = new HashMap<KeyCode, Integer>();
        int upperTile = Maze.SKELETON_MAZE_MAP[(int) player.rowIndex - 1][(int) player.tileIndex];
        int leftTile = Maze.SKELETON_MAZE_MAP[(int) player.rowIndex][(int) player.tileIndex - 1];
        int rightTile = Maze.SKELETON_MAZE_MAP[(int) player.rowIndex][(int) player.tileIndex + 1];
        int bottomTile = Maze.SKELETON_MAZE_MAP[(int) player.rowIndex + 1][(int) player.tileIndex];
        tileDict.put(UP, upperTile);
        tileDict.put(LEFT, leftTile);
        tileDict.put(RIGHT, rightTile);
        tileDict.put(DOWN, bottomTile);
        // Need to check if we trash out the wrong previoustile
        if (direction.getName() == "Up") {
            tileDict.remove(DOWN);
        } else if (direction.getName() == "Left") {
            tileDict.remove(RIGHT);
        } else if (direction.getName() == "Down") {
            tileDict.remove(UP);
        } else if (direction.getName() == "Right") {
            tileDict.remove(LEFT);
        }
        for (Map.Entry tile : tileDict.entrySet()) {
            KeyCode tileDirection = (KeyCode) tile.getKey();
            int tileValue = (int) tile.getValue();
            if (tileValue == Maze.FLOORS) {
                returnedDirection = tileDirection.getName();
                break;
            }
        }
        return returnedDirection;

    }

    public static Boolean checkFloorsValues(int tileValue) {
        if (tileValue == Maze.FLOORS) {
            return true;
        } else {
            return false;
        }
    }

    public static void updatePlayerIndexing(int[] position, Player player) {
        player.rowIndex = (int) position[0];
        player.tileIndex = (int) position[1];
    }

    public static void movePlayer(Player player, KeyCode direction) {
        switch (direction) {
            case UP:
                player.yPos -= player.speed;
                break;
            case LEFT:
                player.xPos -= player.speed;
                break;
            case DOWN:
                player.yPos += player.speed;
                break;
            default:
                player.xPos += player.speed;
                break;
        }
        player.updatePlayerInMaze(Maze.container);
    }

    public static Boolean detectWallsOrStop(KeyCode direction, Player player) {
        int tileIndex = (int) player.tileIndex;
        int rowIndex = (int) player.rowIndex;
        switch (direction) {
            case UP:
                if (rowIndex == 0) {
                    return true;
                }
                int topTile = Maze.SKELETON_MAZE_MAP[rowIndex - 1][tileIndex];
                if (topTile == Maze.STOP | topTile == Maze.PLAYER) {
                    movePlayer(player, direction);
                    return true;
                } else if (topTile == Maze.WALLS) {
                    return true;
                } else {
                    return false;
                }
            case LEFT:
                if (tileIndex == 0) {
                    return true;
                }
                int leftTile = Maze.SKELETON_MAZE_MAP[rowIndex][tileIndex - 1];
                if (leftTile == Maze.STOP | leftTile == Maze.PLAYER) {
                    movePlayer(player, direction);
                    return true;
                } else if (leftTile == Maze.WALLS) {
                    return true;
                } else {
                    return false;
                }
            case DOWN:
                if (rowIndex == Maze.SKELETON_MAZE_MAP[0].length - 1) {
                    return true;
                }
                int downTile = Maze.SKELETON_MAZE_MAP[rowIndex + 1][tileIndex];
                if (downTile == Maze.STOP | downTile == Maze.PLAYER) {
                    movePlayer(player, direction);
                    return true;
                } else if (downTile == Maze.WALLS) {
                    return true;
                } else {
                    return false;
                }

            case RIGHT:
                if (tileIndex == Maze.SKELETON_MAZE_MAP[0].length - 1) {
                    return true;
                }
                int rightTile = Maze.SKELETON_MAZE_MAP[rowIndex][tileIndex + 1];
                if (rightTile == Maze.STOP | rightTile == Maze.PLAYER) {
                    movePlayer(player, direction);
                    return true;
                } else if (rightTile == Maze.WALLS) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }

    }
}
