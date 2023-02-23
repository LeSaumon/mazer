package mazer;

import javafx.scene.input.KeyCode;

public enum Enum {
    UP(KeyCode.valueOf("UP")),
    LEFT(KeyCode.valueOf("LEFT")),
    DOWN(KeyCode.valueOf("DOWN")),
    RIGHT(KeyCode.valueOf("RIGHT"));

    public final KeyCode key;

    private Enum(KeyCode keyCode) {
        this.key = keyCode;
    }
}
