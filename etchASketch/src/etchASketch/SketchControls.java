package etchASketch;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class SketchControls {

    private final Map<Integer, Boolean> keyStates = new HashMap<>();

    public void handleKeyPressed(KeyEvent e) {
        keyStates.put(e.getKeyCode(), true);
    }

    public void handleKeyReleased(KeyEvent e) {
        keyStates.put(e.getKeyCode(), false);
    }

    public boolean isPressed(int keyCode) {
        return keyStates.getOrDefault(keyCode, false);
    }

    public int getMoveDirectionX() {
        boolean left = isPressed(KeyEvent.VK_LEFT);
        boolean right = isPressed(KeyEvent.VK_RIGHT);
        if (left && !right) return -1;
        if (right && !left) return 1;
        return 0;
    }

    public int getMoveDirectionY() {
        boolean up = isPressed(KeyEvent.VK_UP);
        boolean down = isPressed(KeyEvent.VK_DOWN);
        if (up && !down) return -1;
        if (down && !up) return 1;
        return 0;
    }
}
