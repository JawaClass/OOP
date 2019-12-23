package PEngine;

public class PEmpty extends PObject {

    public PEmpty(float ... params) {
        super(new PosRot(params[0], params[1]));
     }
}
