package PEngine;

public class PWall extends PObject {

    public float width;
    public float height;

    public PWall(float... params) {
       super(new PosRot(params[0], params[1]));
        width = params[2];
        height = params[3];
        PCollider = new PRectanglePCollider(posRot, width, height);
        setpDisplay(new PDisplayShape(GameManager.newRectangle(width, height)));

    }


}
