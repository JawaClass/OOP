package PEngine;

import java.util.ArrayList;

public interface PCollissionListener {
    void onHit(PObject other);
    void onAllHit(ArrayList<PObject> allOther);
}
