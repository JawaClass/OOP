package PEngine;

public class PPoint extends PObject {
    PPoint(float ... params) {
        super(new PosRot(params[0], params[1]));
        PCollider = new PointPCollider(posRot);
        setpDisplay(new PDisplayShape(GameManager.newPoint()));
    }
}
