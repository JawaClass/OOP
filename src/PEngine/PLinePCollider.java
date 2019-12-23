package PEngine;

public class PLinePCollider extends PCollider {
    PosRot p2;
    float length;
    PLinePCollider(PosRot posRot, PosRot p2) {
        super(posRot);
        this.p2 = p2;
        length = posRot.dist(p2);
    }

    @Override
    PosRot[] getPoints() {
        return new PosRot[0];
    }
}
