package PEngine;


import java.util.Set;

public abstract class PPlayer implements PControllerEventListener {
// implements ControllerEventListener
    public PObject pObject;
    public PController pController;
//    public PControllerEventListener pControllerEventListener;

    boolean controllerAttached() { return pController != null; }

    void update() {
//        pObject.rotateInDegree(1);
//        pObject.collider.debugCollider();
        if (controllerAttached()) pController.handlepressedKeys();
    }

    public PPlayer() {
        this(null, null);
    }

    public PPlayer(PControllerEventListener pControllerEventListener) {
        this(null, pControllerEventListener);
    }

    public PPlayer(PObject pObject) {
        this(pObject, null);
    }

    public PPlayer(PObject pObject, PControllerEventListener pControllerEventListener) {
        GameManager.pPlayers.add(this);
        this.pObject = pObject;
        if (pControllerEventListener != null) pController = new PController(pControllerEventListener);
    }

        @Override
        public void keyPressed(KEY key) { }

        @Override
        public void keyReleased(KEY key) { }

        @Override
        public void keyHold(Set<KEY> keys) {
//            float unit = 25f;
//            for (KEY key: keys) {
//                switch (key) {
//                    case RIGHT:
//                        pObject.posRot.x+=unit;
//                        break;
//                    case LEFT:
//                        pObject.posRot.x-=unit;
//                        break;
//                    case UP:
//                        pObject.posRot.y-=unit;
//                        break;
//                    case DOWN:
//                        pObject.posRot.y+=unit;
//                        break;
//                }
//            }
        }
}
