package PEngine;

import java.util.Set;

public interface PControllerEventListener {
    void keyPressed(KEY key);
    void keyReleased(KEY key);
    void keyHold(Set<KEY> keys);
}
