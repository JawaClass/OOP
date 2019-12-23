package PEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PController {

    PControllerEventListener PControllerEventListener;
    private static Map<Integer, KEY> int2KeyMap;
    static {
        int2KeyMap = new HashMap<>();
        int2KeyMap.put(37, KEY.LEFT);
        int2KeyMap.put(38, KEY.UP);
        int2KeyMap.put(39, KEY.RIGHT);
        int2KeyMap.put(40, KEY.DOWN);
        int2KeyMap.put(32, KEY.SPACE);
    }

    Set<KEY> pressedButtons = new HashSet<KEY>();

    public PController(PControllerEventListener listener) {
        if (listener == null)
            throw new IllegalArgumentException("listener may not be null");
        this.PControllerEventListener = listener;
    }


    void pressButton(int key) {
        KEY resolvedKey = int2KeyMap.getOrDefault(key, null);
        if (resolvedKey != null) {
            pressedButtons.add(resolvedKey);
            PControllerEventListener.keyPressed(resolvedKey);
        }
    }
    void releaseButton(int key) {
        KEY resolvedKey = int2KeyMap.getOrDefault(key, null);
        if (resolvedKey != null && pressedButtons.contains(resolvedKey)) {
            pressedButtons.remove(resolvedKey);
            PControllerEventListener.keyReleased(resolvedKey);
        }
    }

    void handlepressedKeys() {
        PControllerEventListener.keyHold(pressedButtons);
    }
}
