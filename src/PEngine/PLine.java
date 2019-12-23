package PEngine;

public class PLine extends PObject {
    PosRot p2;
    float length;
    public PLine(float... params) {
        super(new PosRot(params[0], params[1]));
        p2 = new PosRot(params[2], params[3]);
        length = posRot.dist(p2);
        PCollider = new PLinePCollider(posRot, p2);
        setpDisplay(new PDisplayShape(GameManager.newLine(posRot,p2)));
    }
    PosRot middlePoint() { return new PosRot((posRot.x+p2.x)/2f,(posRot.y+p2.y)/2f); }

    @Override
    public void display() {
        super.display();
    }
}
