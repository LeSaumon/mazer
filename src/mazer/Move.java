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
        // Voici l'endroit utilisé a chaque mouvement
        mainScene.setOnKeyPressed(e -> {
            // Ici, on incrémente le compteur de nombre de coup et update le label référent
            player.playCount += 1;
            Maze.updateLabelInContainer(Maze.container, player);
            // Swictch pour gérer les cardinalités des déplacement
            switch (e.getCode()) {
                case UP:
                    // Méthode permettant la détéction des tuile de Mur, ou des tuiles de STOP
                    Boolean upperWall = detectWallsOrStop(UP, player);
                    // Si je ne détecte pas de murs, ni de stop sur le chemin, je déplace mon
                    // personnage
                    if (!upperWall) {
                        // Méthode qui déplacement le positionnement de mon joueur
                        movePlayer(player, UP);
                        // Lancement de la méthode récursive pour continuer le déplacement tant qu'un
                        // état de possibilité de jeu (Carrefour) n'est pas détécté
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
            // On récupère la tuile sur laquelle le joueur se trouve, et celle d'ou il vient
            // en fonction de sa provenance
            int nextTile = getNextTileFromDirection(direction, player);
            int actualTile = getActualTileFromIndexes((int) player.rowIndex, (int) player.tileIndex);
            // Si mon joueur se trouve sur une tuile STOP ou sur la case de départ,
            // l'arréter dessus
            if (actualTile == Maze.STOP | actualTile == Maze.PLAYER) {
                handlePlayerMove(mainScene, player, maze);
            } else {
                // Si la tuile suivante est un mur
                if (nextTile == Maze.WALLS) {
                    // Je vais détecter la prochaine direction
                    String newDirection = detectNextDirection(mainScene, player, maze, direction);
                    // Si la direction recue, n'est pas nulle, j' m'y déplace
                    if (newDirection != "NONE") {
                        KeyCode key = KeyCode.getKeyCode(newDirection);
                        handleRecursivePlayerMove(mainScene, player, maze, key);
                    } else {
                        // Sinon, je retourne a mon choix de déplacement
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
        for (Map.Entry<KeyCode, Integer> tile : tileDict.entrySet()) {
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
        // Dans cette émthode, l'on défini les règles de détection
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
